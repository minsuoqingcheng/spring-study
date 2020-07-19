package cn.edu.nju.lc.springstudy.bean.definition;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Import(BeanDefinitionRegistryDemo.Config.class)
public class BeanDefinitionRegistryDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class(代理XML)
        applicationContext.register(BeanDefinitionRegistryDemo.class);
        applicationContext.refresh();

        //通过 API 注册Bean(命名)
        registerBeanDefinition(applicationContext, "test-2", User.class);

        //通过 API 注册Bean(非命名)
        registerBeanDefinition(applicationContext, null, User.class);

        applicationContext.close();
    }

    /**
     * Bean的注册方式
     * @param registry
     * @param beanName
     * @param beanClass
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        beanDefinitionBuilder.addPropertyValue("id", 1).addPropertyValue("name", "test");
        if (StringUtils.hasText(beanName)) {
            //注册
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry );
        }

    }



    @Component
    public static class Config {

        @Bean(name = {"user", "user-test"})
        public User user() {
            User user = new User();
            user.setId(-1L);
            user.setName("auto");
            return user;
        }

    }



}
