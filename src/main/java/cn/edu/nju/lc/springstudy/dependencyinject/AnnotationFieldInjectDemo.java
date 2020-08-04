package cn.edu.nju.lc.springstudy.dependencyinject;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class AnnotationFieldInjectDemo {

    @Autowired
    private UserHolder userHolder;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationFieldInjectDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();


        AnnotationFieldInjectDemo demo = applicationContext.getBean(AnnotationFieldInjectDemo.class);
        System.out.println(demo.userHolder);

        applicationContext.close();

    }

    @Bean
    private UserHolder userHolder(User user) {
        return new UserHolder(user);
    }



}
