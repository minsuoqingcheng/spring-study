package cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.enums.City;
import lombok.Data;

import java.util.List;
import java.util.Properties;

@Data
public class User {

    private Long id;

    private String name;

    private City city;

    private City[] cities;

    private List<City> lifeCities;

    private Company company;

    private Properties context;

    private String contextAsString;

    public static User createUser() {
        return new User();
    }

}
