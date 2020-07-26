package cn.edu.nju.lc.springstudy.dependencylookup;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class ObjectProviderDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);

        lookupByStream(applicationContext);

        applicationContext.close();
    }


    @Bean
    @Primary
    public String helloWorld() {    //Bean的名称 = 方法名称
        return "Hello World";
    }

    @Bean
    public String helloWord() {    //Bean的名称 = 方法名称
        return "Hello china";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
//        for (String anObjectProvider : objectProvider) {
//            System.out.println(anObjectProvider);
//        }
        System.out.println(objectProvider.getObject());
    }


    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        System.out.println(objectProvider.getIfAvailable(User::createUser));
    }

    private static void lookupByStream(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        objectProvider.stream().forEach(System.out::println);
    }

}
