package cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.annotation.Super;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Super
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class SupperUser extends User {

    private String address;

}
