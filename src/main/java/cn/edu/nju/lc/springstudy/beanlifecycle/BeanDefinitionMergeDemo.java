package cn.edu.nju.lc.springstudy.beanlifecycle;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

@SuppressWarnings("Duplicates")
public class BeanDefinitionMergeDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String location = "META-INF/dependency-lookup-context.xml";
        //指定字符编码
        Resource resource = new ClassPathResource(location);    //不用ClassPathResource，前面需要加classpath
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanNums = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载的Bean数量："+beanNums);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }

}
