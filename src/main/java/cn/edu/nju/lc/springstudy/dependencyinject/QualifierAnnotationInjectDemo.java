package cn.edu.nju.lc.springstudy.dependencyinject;

import cn.edu.nju.lc.springstudy.dependencyinject.annotation.InjectUser;
import cn.edu.nju.lc.springstudy.dependencyinject.annotation.UserGroup;
import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

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


    @InjectUser
    private User injectUser;


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


//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        Set<Class<? extends Annotation>> annotationTypes = new HashSet<>();
//        annotationTypes.add(Autowired.class);
//        annotationTypes.add(InjectUser.class);
//        beanPostProcessor.setAutowiredAnnotationTypes(annotationTypes);
//        return beanPostProcessor;
//    }


    @Bean
    @Order(value = PriorityOrdered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
        return beanPostProcessor;
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

        System.out.println("injectUser: " + demo.injectUser);

        applicationContext.close();

    }

}
