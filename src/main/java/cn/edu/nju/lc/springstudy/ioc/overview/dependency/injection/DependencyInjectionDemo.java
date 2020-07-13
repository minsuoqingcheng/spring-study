package cn.edu.nju.lc.springstudy.ioc.overview.dependency.injection;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import cn.edu.nju.lc.springstudy.ioc.overview.dependency.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/dependency-injection-context.xml"
        );

        //自定义Bean
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository);

        //依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == beanFactory);

        ObjectFactory<User> userObjectFactory = userRepository.getUserObjectFactory();
        System.out.println(userObjectFactory.getObject());

        System.out.println(userRepository.getApplicationContextObjectFactory().getObject());

        //容器内建Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);

    }

}
