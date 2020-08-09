package cn.edu.nju.lc.springstudy.dependencyinject;

import cn.edu.nju.lc.springstudy.dependencyinject.annotation.UserGroup;
import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

public class QualifierAnnotationInjectDemo {

    @Autowired
    private User user;      // primary

    @Autowired
    @Qualifier("user")
    private User namedUser;

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifierUser;

    @Autowired
    @UserGroup
    private Collection<User> userGroup;


    @Bean
    @Qualifier
    private User user1() {
        User user = new User();
        user.setId(-1L);
        return user;
    }

    @Bean
    @Qualifier
    private User user2() {
        User user = new User();
        user.setId(-2L);
        return user;
    }

    @Bean
    @UserGroup
    private User user3() {
        User user = new User();
        user.setId(-3L);
        return user;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationInjectDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        QualifierAnnotationInjectDemo demo = applicationContext.getBean(QualifierAnnotationInjectDemo.class);

        System.out.println("user: " + demo.user);
        System.out.println("namedUser: " + demo.namedUser);

        System.out.println("allUser: " + demo.allUsers);
        System.out.println("qualifierUser: " + demo.qualifierUser);

        System.out.println("userGroup: " + demo.userGroup);

        applicationContext.close();

    }

}
