package cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain;

import lombok.Data;

@Data
public class UserHolder {

    private int number;
    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }
}
