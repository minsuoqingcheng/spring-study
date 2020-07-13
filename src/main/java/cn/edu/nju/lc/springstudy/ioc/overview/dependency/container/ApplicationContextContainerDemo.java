package cn.edu.nju.lc.springstudy.ioc.overview.dependency.container;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;


public class ApplicationContextContainerDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类
        applicationContext.register(ApplicationContextContainerDemo.class);
        applicationContext.refresh();

        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);
        System.out.println(beansOfType);

    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(-1L);
        user.setName("auto");
        return user;
    }

}
