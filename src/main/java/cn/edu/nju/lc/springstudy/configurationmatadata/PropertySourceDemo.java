package cn.edu.nju.lc.springstudy.configurationmatadata;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

@org.springframework.context.annotation.PropertySource("classpath:/META-INF/user.properties")
public class PropertySourceDemo {

    @Bean
    public User configUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(PropertySourceDemo.class);

        Map<String, Object> map = new HashMap<>();
        map.put("user.name", "first");
        //PropertySource 先加入的优先级高
        PropertySource propertySource = new MapPropertySource("first-property-source", map);
        context.getEnvironment().getPropertySources().addFirst(propertySource);

        context.refresh();

        Map<String, User> userMap = context.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }


        context.close();

    }

}
