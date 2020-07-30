package cn.edu.nju.lc.springstudy.dependencyinject;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import lombok.Data;

@Data
public class UserHolder {

    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }
}
