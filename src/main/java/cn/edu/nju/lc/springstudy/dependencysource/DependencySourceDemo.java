package cn.edu.nju.lc.springstudy.dependencysource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

public class DependencySourceDemo {

    // 注入在postProcessProperties执行，早于@PostConstruct
    /**
     *  AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory)
     *
     *  beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
        beanFactory.registerResolvableDependency(ResourceLoader.class, this);
        beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
        beanFactory.registerResolvableDependency(ApplicationContext.class, this);

        这四个对象是*非Spring容器管理对象*，只能依赖注入，无法依赖查找，通过BeanFactory#getBean方法会报NoSuchBeanDefinitionException
     */
    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);

        applicationContext.refresh();



        applicationContext.close();
    }

}
