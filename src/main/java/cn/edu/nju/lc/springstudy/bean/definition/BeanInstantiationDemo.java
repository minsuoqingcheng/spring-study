package cn.edu.nju.lc.springstudy.bean.definition;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean实例化
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/bean-instantiation-test.xml"
        );
        User user = beanFactory.getBean("user-by-static", User.class);
        System.out.println(user);

        User user2 = beanFactory.getBean("user-by-instance-method", User.class);
        System.out.println(user2);

        User user3 = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(user3);
    }

}
