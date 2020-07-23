package cn.edu.nju.lc.springstudy.dependencylookup;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookupDemo.class);
        applicationContext.refresh();

        //ConfigurableListableBeanFactory -> ConfigurableBeanFactory -> HierarchicalBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println(beanFactory.getParentBeanFactory());

        //设置 parent BeanFactory
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println(beanFactory.getParentBeanFactory());

        displayLocalBean(beanFactory, "user");
        displayLocalBean(parentBeanFactory, "user");

        System.out.println("iterator find :" + containsBean(beanFactory, "user"));
        System.out.println("iterator find :" + containsBean(parentBeanFactory, "user"));

        Map<String, User> userMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(beanFactory, User.class);
        System.out.println(userMap);

        applicationContext.close();
    }


    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            return containsBean(hierarchicalBeanFactory, beanName);
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.println("is contains " + beanName + " :" + beanFactory.containsLocalBean(beanName));
    }


    private static HierarchicalBeanFactory createParentBeanFactory() {
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //加载配置
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //Bean定义的数量
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }

}
