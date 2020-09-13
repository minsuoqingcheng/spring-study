package cn.edu.nju.lc.springstudy.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;

public class InjectResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource resource;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] resources;

    @PostConstruct
    public void init() throws IOException {
        System.out.println(ResourceUtil.getContent(resource));

        System.out.println("=============List===============");

        for (Resource resource : resources) {
            System.out.println(ResourceUtil.getContent(resource));
        }
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectResourceDemo.class);

        context.refresh();

        context.close();


    }

}
