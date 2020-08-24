package cn.edu.nju.lc.springstudy.beanlifecycle;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

public class BeanMataDataConfigurationDemo {


    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader definitionReader = new PropertiesBeanDefinitionReader(beanFactory);

        String location = "META-INF/user.properties";
        //指定字符编码
        Resource resource = new ClassPathResource(location);    //不用ClassPathResource，前面需要加classpath
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanNums = definitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载的Bean数量："+beanNums);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);


    }

}
