package cn.edu.nju.lc.springstudy.dependencylookup;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TypeSafeDependencyLookupDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafeDependencyLookupDemo.class);
        applicationContext.refresh();

        //单一类型
        displayBeanFactoryGetBean(applicationContext);
        displayObjectFactoryGetObject(applicationContext);
        displayObjectProviderGetObject(applicationContext);

        System.out.println("==============List============");
        //集合类型 安全
        displayListableBeanFactory(applicationContext);

        applicationContext.close();
    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException(() -> beanFactory.getBean(User.class));
    }

    private static void displayObjectFactoryGetObject(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = beanFactory.getBeanProvider(User.class);
        printBeansException(objectFactory::getObject);
    }

    private static void displayObjectProviderGetObject(BeanFactory beanFactory) {
        ObjectProvider<User> objectFactory = beanFactory.getBeanProvider(User.class);
        printBeansException(objectFactory::getIfAvailable);
    }

    private static void displayListableBeanFactory(ListableBeanFactory applicationContext) {
        printBeansException(() -> applicationContext.getBeansOfType(User.class));
    }


    private static void printBeansException(Runnable runnable) {
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
