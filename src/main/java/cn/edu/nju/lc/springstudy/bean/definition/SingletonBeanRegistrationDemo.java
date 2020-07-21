package cn.edu.nju.lc.springstudy.bean.definition;

import cn.edu.nju.lc.springstudy.bean.factory.DefaultUserFactory;
import cn.edu.nju.lc.springstudy.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("userFactory", userFactory);

        applicationContext.refresh();

        UserFactory userFactory1 = beanFactory.getBean("userFactory", UserFactory.class);

        System.out.println(userFactory == userFactory1);


        applicationContext.close();
    }

}
