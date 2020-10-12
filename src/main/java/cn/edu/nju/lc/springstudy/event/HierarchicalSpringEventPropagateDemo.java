package cn.edu.nju.lc.springstudy.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

public class HierarchicalSpringEventPropagateDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        parentContext.register(MyListener.class);

        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        currentContext.register(MyListener.class);

        currentContext.setParent(parentContext);

        parentContext.refresh();
        currentContext.refresh();

        parentContext.close();
        currentContext.close();
    }


    private static class MyListener implements ApplicationListener<ContextRefreshedEvent> {


        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.println("监听到 Spring 应用上下文：" + event.getApplicationContext().getId());
        }
    }
}
