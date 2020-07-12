package cn.edu.nju.lc.springstudy.ioc.overview.dependency.lookup;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.annotation.Super;
import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependencyLookupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/dependency-lookup-context.xml"
        );

        //实时查找
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);

        //延迟查找
        ObjectFactory<User> userObjectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User lazyUser = userObjectFactory.getObject();
        System.out.println(lazyUser);

        //根据bean查找集合
        ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
        Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
        System.out.println("all users:" + users);

        //根据Class和annotation查找
        Map<String, User> beansWithAnnotation = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
        System.out.println("with annotation users: " + beansWithAnnotation);

    }

}
