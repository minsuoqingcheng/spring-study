package cn.edu.nju.lc.springstudy.annotations.conditional;


import org.springframework.context.annotation.*;
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
//    @Profile("even")
    @Conditional(EvenConditional.class)
    public Integer even() {
        return 2;
    }
}
