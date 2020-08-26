package cn.edu.nju.lc.springstudy.beanlifecycle;

import cn.edu.nju.lc.springstudy.dependencyinject.AnnotationSetterInjectDemo;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

public class AnnotatedBeanDefinitionParserDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader definitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

        int beanNumerBefore = beanFactory.getBeanDefinitionCount();
        definitionReader.register(AnnotatedBeanDefinitionParserDemo.class);
        int beanNumberAfter = beanFactory.getBeanDefinitionCount();
        System.out.println("count: " + (beanNumberAfter - beanNumerBefore));

        //普通类注册到Spring IOC容器，Bean名称首字母小写
        AnnotatedBeanDefinitionParserDemo demoBean = beanFactory.getBean("annotatedBeanDefinitionParserDemo", AnnotatedBeanDefinitionParserDemo.class);
        System.out.println(demoBean);

    }

}
