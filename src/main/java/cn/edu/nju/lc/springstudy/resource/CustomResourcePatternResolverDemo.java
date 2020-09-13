package cn.edu.nju.lc.springstudy.resource;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;

public class CustomResourcePatternResolverDemo {

    public static void main(String[] args) throws IOException {

        //读取当前package下的所有 .java 文件

        String currentPackageFilePath = "/" + System.getProperty("user.dir") + "/src/main/java/cn/edu/nju/lc" +
                "/springstudy/resource/";

        String locationPattern = currentPackageFilePath + "*.java";

        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver(resourceLoader);
        patternResolver.setPathMatcher(new JavaFilePatternMatcher());


        Resource[] resources = patternResolver.getResources(locationPattern);
        for (Resource resource : resources) {
            System.out.println(ResourceUtil.getContent(resource));
        }

    }


    static class JavaFilePatternMatcher implements PathMatcher {

        @Override
        public boolean isPattern(String s) {
            return s.endsWith(".java");
        }

        @Override
        public boolean match(String s, String s1) {
            return s1.equals(".java");
        }

        @Override
        public boolean matchStart(String s, String s1) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String s, String s1) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String s, String s1) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String s) {
            return null;
        }

        @Override
        public String combine(String s, String s1) {
            return null;
        }
    }

}
