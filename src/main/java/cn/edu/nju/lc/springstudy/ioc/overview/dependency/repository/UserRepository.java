package cn.edu.nju.lc.springstudy.ioc.overview.dependency.repository;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import lombok.Data;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;

import java.util.Collection;

@Data
public class UserRepository {

    private Collection<User> users;     //自定义Bean

    private BeanFactory beanFactory;    //内建非Bean对象（依赖）

    private ObjectFactory<User> userObjectFactory;

    private ObjectFactory<ApplicationContext> applicationContextObjectFactory;

}
