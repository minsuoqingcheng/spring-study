package cn.edu.nju.lc.springstudy.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.io.IOException;

public class InjectResourceLoaderDemo implements ResourceLoaderAware {


    private ResourceLoader resourceLoader;

    @Autowired
    private ResourceLoader autowiredResourceLoader;

    @Autowired
    private AbstractApplicationContext abstractApplicationContext;

    @PostConstruct
    public void init() throws IOException {
        System.out.println(resourceLoader == autowiredResourceLoader);
        System.out.println(resourceLoader == autowiredResourceLoader);
    }


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectResourceLoaderDemo.class);

        context.refresh();


        context.close();


    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
