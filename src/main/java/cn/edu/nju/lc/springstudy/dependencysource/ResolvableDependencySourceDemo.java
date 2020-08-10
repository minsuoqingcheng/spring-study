package cn.edu.nju.lc.springstudy.dependencysource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencySourceDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            ConfigurableListableBeanFactory listableBeanFactory = ConfigurableListableBeanFactory.class.cast(beanFactory);
            listableBeanFactory.registerResolvableDependency(String.class, "Hello World!");
        });

        applicationContext.refresh();

        applicationContext.close();
    }

}
