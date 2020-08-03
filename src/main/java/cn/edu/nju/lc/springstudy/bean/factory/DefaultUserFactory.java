package cn.edu.nju.lc.springstudy.bean.factory;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @Override
    public User createUser() {
        return User.createUser();
    }

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct: UserFactory 初始化中");
    }


    public void initUserFactory() {
        System.out.println("自定义初始化方法: UserFactory 初始化中");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet: UserFactory 初始化中");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: UserFactory 销毁中");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy: UserFactory 销毁中");
    }

    public void doDestroy() {
        System.out.println("自定义销毁方法: UserFactory 销毁中");
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        System.out.println("UserFactory对象正在被回收");
    }
}