package cn.edu.nju.lc.springstudy.configurationmatadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class XmlYamlPropertySourceDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        reader.loadBeanDefinitions("/META-INF/yaml-property-scource-context.xml");

        Map<String, Object> map = beanFactory.getBean("yamlMap", Map.class);
        System.out.println(map);


    }

}
