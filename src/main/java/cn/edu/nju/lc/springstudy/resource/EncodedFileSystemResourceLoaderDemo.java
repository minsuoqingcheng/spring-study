package cn.edu.nju.lc.springstudy.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

public class EncodedFileSystemResourceLoaderDemo {

    public static void main(String[] args) throws IOException {

        String currentJavaFilePath = "/" + System.getProperty("user.dir") + "/src/main/java/cn/edu/nju/lc" +
                "/springstudy/resource/EncodedFileSystemResourceLoaderDemo.java";
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource(currentJavaFilePath);

        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

        try (Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }

    }

}
