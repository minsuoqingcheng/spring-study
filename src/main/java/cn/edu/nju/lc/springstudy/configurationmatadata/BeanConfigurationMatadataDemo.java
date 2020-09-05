package cn.edu.nju.lc.springstudy.configurationmatadata;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

public class BeanConfigurationMatadataDemo {

    public static void main(String[] args) {

        // 声明BeanDefinition的来源
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("name", "test");

        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        //附加属性——>不影响Bean的生成（populate）
        //属性（存储）上下文
        beanDefinition.setAttribute("name", "test2");
        //Bean来自何处（辅助作用）
        beanDefinition.setSource(BeanConfigurationMatadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("user", beanDefinition);

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    if (BeanConfigurationMatadataDemo.class.equals(bd.getSource())) {
                        String name = (String) bd.getAttribute("name");
                        User user = (User) bean;
                        user.setName(name);
                    }
                }
                return bean;
            }
        });

        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }

}
