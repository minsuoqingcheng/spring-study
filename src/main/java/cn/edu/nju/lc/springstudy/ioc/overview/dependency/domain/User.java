package cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.enums.City;
import lombok.Data;

import java.util.List;

@Data
public class User {

    private Long id;

    private String name;

    private City city;

    private City[] cities;

    private List<City> lifeCities;

    public static User createUser() {
        return new User();
    }

}
