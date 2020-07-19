package cn.edu.nju.lc.springstudy.bean.definition;

import cn.edu.nju.lc.springstudy.bean.factory.DefaultUserFactory;
import cn.edu.nju.lc.springstudy.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ServiceLoader;

public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/special-bean-instantiation-test.xml"
        );

        serviceLoader();

        ServiceLoader<UserFactory> userFactoryServiceLoader = applicationContext.getBean("userFactoryServiceLoader", ServiceLoader.class);
        for (UserFactory userFactory : userFactoryServiceLoader) {
            System.out.println(userFactory.createUser());
        }

        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        UserFactory userFactory = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());
    }


    private static void serviceLoader() {
        ServiceLoader<UserFactory> userFactories = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        for (UserFactory userFactory : userFactories) {
            System.out.println(userFactory.createUser());
        }
    }
}
