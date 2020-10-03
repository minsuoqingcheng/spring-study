package cn.edu.nju.lc.springstudy.databinding;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.util.stream.Stream;

public class JavaBeansDemo {

    public static void main(String[] args) throws Exception {

        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Stream.of(propertyDescriptors).forEach(System.out::println);

        System.out.println("---------------------------");

        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        Stream.of(methodDescriptors).forEach(System.out::println);
    }

}
