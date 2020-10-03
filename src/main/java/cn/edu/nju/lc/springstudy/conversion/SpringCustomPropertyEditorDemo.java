package cn.edu.nju.lc.springstudy.conversion;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCustomPropertyEditorDemo {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/property-editor-context.xml");

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        applicationContext.close();
    }

}
