package cn.edu.nju.lc.springstudy.bean.definition;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {

        //通过BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //通过属性设置
        beanDefinitionBuilder.addPropertyValue("age", 24);
        beanDefinitionBuilder.addPropertyValue("name", "test"); //builder
        //获取实例
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();


        // 通过 AbstractBeanDefinition 派生
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("age", 24);
        genericBeanDefinition.setPropertyValues(propertyValues);
    }

}
