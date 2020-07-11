package cn.edu.nju.lc.springstudy.ioc.java.beans;

import cn.edu.nju.lc.springstudy.properties.Person;

import java.beans.*;
import java.util.stream.Stream;

public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {


        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(System.out::println);
        //java.beans.PropertyDescriptor[name=age; propertyType=class java.lang.Integer; readMethod=public java.lang.Integer cn.edu.nju.lc.springstudy.properties.Person.getAge(); writeMethod=public void cn.edu.nju.lc.springstudy.properties.Person.setAge(java.lang.Integer)]
        //java.beans.PropertyDescriptor[name=class; propertyType=class java.lang.Class; readMethod=public final native java.lang.Class java.lang.Object.getClass()]
        //java.beans.PropertyDescriptor[name=id; propertyType=class java.lang.Long; readMethod=public java.lang.Long cn.edu.nju.lc.springstudy.properties.Person.getId(); writeMethod=public void cn.edu.nju.lc.springstudy.properties.Person.setId(java.lang.Long)]
        //java.beans.PropertyDescriptor[name=name; propertyType=class java.lang.String; readMethod=public java.lang.String cn.edu.nju.lc.springstudy.properties.Person.getName(); writeMethod=public void cn.edu.nju.lc.springstudy.properties.Person.setName(java.lang.String)]

        System.out.println();

        //Class<?> beanClass, Class<?> stopClass
        BeanInfo beanInfo2 = Introspector.getBeanInfo(Person.class, Object.class);
        Stream.of(beanInfo2.getPropertyDescriptors())
                .forEach(System.out::println);

        /**
         * Spring中实现类型转换
         * PropertyDescriptor中可以添加PropertyEditor,进行类型转换
         */
        Stream.of(beanInfo2.getPropertyDescriptors())
                .forEach(p -> {
                    p.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                });
    }


    class StringToIntegerPropertyEditor extends PropertyEditorSupport {
        public void setAsText(String text) throws java.lang.IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }

}
