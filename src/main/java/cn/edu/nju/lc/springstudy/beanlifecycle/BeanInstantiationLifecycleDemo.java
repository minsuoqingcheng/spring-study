package cn.edu.nju.lc.springstudy.beanlifecycle;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.SupperUser;
import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

@SuppressWarnings("Duplicates")
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String location = "META-INF/dependency-lookup-context.xml";
        //指定字符编码
        Resource resource = new ClassPathResource(location);    //不用ClassPathResource，前面需要加classpath
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanNums = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载的Bean数量："+beanNums);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        SupperUser supperUser = beanFactory.getBean("superUser", SupperUser.class);
        System.out.println(supperUser);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }


    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("superUser", beanName) && SupperUser.class.equals(beanClass)) {
                return new SupperUser();    //覆盖Bean
            } else {
                return null;    //保持Spring IOC容器的实例化操作
            }
        }

        //这个方法返回false后续都不会执行
        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                User user = (User) bean;
                user.setId(90L);
                return false;    //user对象的元信息都会被忽略，不会被赋值
            }
            return true;
        }


        //user 跳过Bean属性的植入
        //superUser 跳过初始化
        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
                MutablePropertyValues propertyValues;
                if (pvs instanceof MutablePropertyValues) {
                    propertyValues = (MutablePropertyValues) pvs;
                } else {
                    propertyValues = new MutablePropertyValues();
                }
                if (propertyValues.contains("number")) {
                    //PropertyValue值不可以修改，需要remove再add
                    propertyValues.removePropertyValue("number");
                    propertyValues.addPropertyValue("number", "1");
                }
                return propertyValues;
            }
            return null;
        }
    }


}
