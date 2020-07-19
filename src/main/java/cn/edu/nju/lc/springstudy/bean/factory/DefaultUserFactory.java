package cn.edu.nju.lc.springstudy.bean.factory;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;

public class DefaultUserFactory implements UserFactory {

    @Override
    public User createUser() {
        return User.createUser();
    }
}
