package cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.enums.City;
import lombok.Data;

@Data
public class User {

    private Long id;

    private String name;

    private City[] cities;

    public static User createUser() {
        return new User();
    }

}
