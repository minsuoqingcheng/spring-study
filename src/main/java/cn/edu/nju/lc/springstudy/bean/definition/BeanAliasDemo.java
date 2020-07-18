package cn.edu.nju.lc.springstudy.bean.definition;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasDemo {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/bean-definition-test.xml"
        );

        //实时查找
        User user = beanFactory.getBean("user", User.class);
        User beanUser = beanFactory.getBean("bean-test-1", User.class);
        System.out.println(user == beanUser);   //true


    }

}
