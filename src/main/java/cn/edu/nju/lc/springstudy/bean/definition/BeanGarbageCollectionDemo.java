package cn.edu.nju.lc.springstudy.bean.definition;

import cn.edu.nju.lc.springstudy.bean.factory.DefaultUserFactory;
import cn.edu.nju.lc.springstudy.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();

        applicationContext.close();
        System.gc();

    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    @Lazy
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }

}
