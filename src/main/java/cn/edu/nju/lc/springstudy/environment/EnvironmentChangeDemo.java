package cn.edu.nju.lc.springstudy.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentChangeDemo {

    @Value("${user.name}")
    private String username;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnvironmentChangeDemo.class);


        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();

        Map<String, Object> map = new HashMap<>();
        map.put("user.name", "test");
        MapPropertySource mapPropertySource = new MapPropertySource("first-ps", map);
        propertySources.addFirst(mapPropertySource);

        context.refresh();

        EnvironmentChangeDemo demo = context.getBean(EnvironmentChangeDemo.class);
        System.out.println(demo.username);

        context.close();

    }

}
