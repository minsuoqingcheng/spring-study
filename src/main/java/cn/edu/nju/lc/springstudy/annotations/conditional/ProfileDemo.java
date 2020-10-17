package cn.edu.nju.lc.springstudy.annotations.conditional;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class ProfileDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ProfileDemo.class);

        ConfigurableEnvironment environment = context.getEnvironment();
        environment.setDefaultProfiles("odd");  //默认Profile

        //增加活跃的Profile
        environment.setActiveProfiles("even");

        context.refresh();

        Integer number = context.getBean("number", Integer.class);
        System.out.println(number);

        context.close();

    }


    @Bean(name = "number")
    @Profile("odd")
    public Integer odd() {
        return 1;
    }

    @Bean(name = "number")
    @Profile("even")
    public Integer even() {
        return 2;
    }
}
