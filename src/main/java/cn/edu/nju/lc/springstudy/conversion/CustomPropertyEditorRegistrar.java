package cn.edu.nju.lc.springstudy.conversion;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {

        registry.registerCustomEditor(User.class, "context", new StringToPropertiesPropertyEditor());

    }
}
