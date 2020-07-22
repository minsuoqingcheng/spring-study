package cn.edu.nju.lc.springstudy.dependencylookup;

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

}
