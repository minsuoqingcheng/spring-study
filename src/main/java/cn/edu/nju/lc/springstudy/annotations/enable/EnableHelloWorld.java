package cn.edu.nju.lc.springstudy.annotations.enable;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Import(HelloWorldConfiguration.class)            //方法一：基于Configuration Class
//@Import(HelloWorldImportSelector.class)           //方法二：基于ImportSelector接口实现
@Import(HelloWorldBeanDefinitionRegistrar.class)    //方法三：基于ImportBeanDefinitionRegistrar接口实现
public @interface EnableHelloWorld {
}
