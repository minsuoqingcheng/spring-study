package cn.edu.nju.lc.springstudy.dependencysource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource(value = "META-INF/default.properties", encoding = "UTF-8")
public class ExternalConfigDependencySourceDemo {

    @Value("${user.id:-1}")
    private Long id;

    @Value("${usr.name}")
    private String name;

    @Value("${user.resource:classpath://META_INF/default.properties}")
    private Resource resource;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ExternalConfigDependencySourceDemo.class);

        applicationContext.refresh();

        ExternalConfigDependencySourceDemo demo = applicationContext.getBean(ExternalConfigDependencySourceDemo.class);

        System.out.println(demo.id);
        System.out.println(demo.name);
        System.out.println(demo.resource);

        applicationContext.close();
    }

}
