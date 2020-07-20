package cn.edu.nju.lc.springstudy.bean.definition;

import cn.edu.nju.lc.springstudy.bean.factory.DefaultUserFactory;
import cn.edu.nju.lc.springstudy.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean初始化
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();

        //非延迟初始化在Spring应用上下文启动完成过程中被初始化
        System.out.println("Spring应用上下文已启动...");
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        System.out.println("Spring应用上下文准备关闭...");
        applicationContext.close();
        System.out.println("Spring应用上下文已关闭...");

    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    @Lazy
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }

}
