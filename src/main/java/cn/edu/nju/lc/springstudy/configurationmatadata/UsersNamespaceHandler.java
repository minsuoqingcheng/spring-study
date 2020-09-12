package cn.edu.nju.lc.springstudy.configurationmatadata;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class UsersNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
