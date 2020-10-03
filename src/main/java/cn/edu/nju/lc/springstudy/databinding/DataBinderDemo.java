package cn.edu.nju.lc.springstudy.databinding;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

public class DataBinderDemo {

    public static void main(String[] args) {

        User user = new User();
        DataBinder binder = new DataBinder(user, "user");

        //创建PropertyValues
        Map<String, Object> source = new HashMap<>();
        source.put("id", 1);
        source.put("name", "test");
        source.put("age", 10);
        //嵌套属性绑定
        source.put("company.name", "alibaba");
        PropertyValues propertyValues = new MutablePropertyValues(source);

        //不允许忽略未知字段
//        binder.setIgnoreUnknownFields(false);

        //调整 ignoreInvalidFields
//        binder.setAutoGrowNestedPaths(false);
//        binder.setIgnoreInvalidFields(true);

        binder.setRequiredFields("id", "name", "city");

        binder.bind(propertyValues);

        System.out.println(binder.getBindingResult());
        System.out.println(user);


    }

}
