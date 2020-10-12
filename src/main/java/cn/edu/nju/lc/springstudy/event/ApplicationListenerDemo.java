package cn.edu.nju.lc.springstudy.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
public class ApplicationListenerDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ApplicationListenerDemo.class);

        applicationContext.register(MyApplicationListener.class);

        //基于接口
        applicationContext.addApplicationListener(event -> System.out.println("事件：" + event));

        applicationContext.refresh();

        applicationContext.close();

    }

    //作为 Spring Bean注册
    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.println("spring bean receive event: " + event);
        }
    }


    //基于注解
    @EventListener
    @Order(2)   //同一个事件的优先级控制
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("注解Refresh事件：" + event);
    }


    @EventListener
    @Order(1)
    public void onApplicationEvent2(ContextRefreshedEvent event) {
        System.out.println("注解Refresh事件(优先级更高)：" + event);
    }

    @EventListener
    @Async
    public void onApplicationEventAsync(ContextRefreshedEvent event) {
        System.out.println(Thread.currentThread().getName() + "----注解事件：" + event);
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("注解Close事件：" + event);
    }

}
