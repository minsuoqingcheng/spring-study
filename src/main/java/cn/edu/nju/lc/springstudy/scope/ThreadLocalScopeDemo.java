package cn.edu.nju.lc.springstudy.scope;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User user() {
        User user = new User();
        user.setId(System.currentTimeMillis());
        return user;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });

        applicationContext.refresh();

        for (int i = 0; i < 3; i++) {
            User user = applicationContext.getBean("user", User.class);
            System.out.println("user" + " i :"  + user);
        }

        System.out.println("================in thread================");

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                User user = applicationContext.getBean("user", User.class);
                System.out.println("user" + " i :"  + user);
            });
            //线程启动
            thread.start();
        }

        applicationContext.close();

    }

}
