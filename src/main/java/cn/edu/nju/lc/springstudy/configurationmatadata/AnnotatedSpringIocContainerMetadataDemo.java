package cn.edu.nju.lc.springstudy.configurationmatadata;


import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/user.properties")      //java8 @Repeatable
@PropertySource("classpath:/META-INF/user.properties")
public class AnnotatedSpringIocContainerMetadataDemo {

    /**
     * user.name 默认是系统用户
     * @param id
     * @param name
     * @return
     */
    @Bean
    public User configUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedSpringIocContainerMetadataDemo.class);

        context.refresh();

        Map<String, User> userMap = context.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        context.close();

    }

}
