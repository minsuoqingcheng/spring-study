package cn.edu.nju.lc.springstudy.i18n;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 自动刷新
 *
 * 1、定位资源位置（文件）
 * 2、初始化 Property 对象
 * 3、实现resolveCode方法
 * 4、监听资源文件（Java NIO2 WatchService）
 * 5、使用线程池处理变更事件
 * 6、重新装载 Property 对象
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private static final String resourceName = "messages.properties";

    private static final String resourcePath = "/META-INF/messages.properties";

    private ExecutorService executorService;

    private Resource resource;

    private final Properties properties;

    private ResourceLoader resourceLoader;

    public DynamicResourceMessageSource() {
        this.resource = getMessageResource();
        this.properties = loadMessageProperties();
        this.executorService = Executors.newSingleThreadExecutor();
        onMessagePropertiesChanged();
    }


    private void onMessagePropertiesChanged() {
        if (this.resource.isFile()) {
            //获取对应文件系统中的文件
            try {
                File file = this.resource.getFile();
                Path path = file.toPath();
                //新建WatchService
                FileSystem fileSystem = FileSystems.getDefault();
                WatchService watchService = fileSystem.newWatchService();
                //注册WatchService到path,并且关心修改事件
                Path dir = path.getParent();
                dir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
                //处理资源文件变化
                processMessagePropertiesChanged(watchService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processMessagePropertiesChanged(WatchService watchService) {
        executorService.submit(() -> {
            while (true) {
                WatchKey watchKey = watchService.take();    //take会阻塞
                //WatchKey是否有效
                try {
                    if (watchKey.isValid()) {
                        Path dirPath = (Path) watchKey.watchable();
                        for (WatchEvent event : watchKey.pollEvents()) {
                            //事件发生源(相对路径)
                            Path fileRelativePath = (Path) event.context();
                            if (resourceName.equals(fileRelativePath.getFileName().toString())) {
                                //处理为绝对路径
                                Path filePath = dirPath.resolve(fileRelativePath);
                                System.out.println("修改的文件：" + filePath);
                                File file = filePath.toFile();
                                Properties newProperties = loadMessageProperties(new FileReader(file));
                                synchronized (properties) {
                                    properties.clear();
                                    properties.putAll(newProperties);
                                }
                            }
                        }
                    }
                } finally {
                    if (Objects.nonNull(watchKey)) {
                        watchKey.reset();
                    }
                }

            }
        });
    }

    private Properties loadMessageProperties() {
        Resource resource = getMessageResource();
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        try {
            return loadMessageProperties(encodedResource.getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Properties loadMessageProperties(Reader reader) {
        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    private Resource getMessageResource() {
        ResourceLoader resourceLoader = getResourceLoader();
        return resourceLoader.getResource(resourcePath);
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messagePattern = properties.getProperty(code);
        if (StringUtils.hasText(messagePattern)) {
            return new MessageFormat(messagePattern, locale);
        }
        return null;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private ResourceLoader getResourceLoader() {
        return resourceLoader == null ? new DefaultResourceLoader() : resourceLoader;
    }

    public static void main(String[] args) throws InterruptedException {
        DynamicResourceMessageSource messageSource = new DynamicResourceMessageSource();
        for (int i = 0; i < 10000; i++) {
            String message = messageSource.getMessage("name", new Object[]{}, Locale.getDefault());
            System.out.println(message);
            Thread.sleep(1000);
        }

    }
}
