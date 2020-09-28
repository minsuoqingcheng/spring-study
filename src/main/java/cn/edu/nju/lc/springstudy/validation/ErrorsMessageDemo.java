package cn.edu.nju.lc.springstudy.validation;

import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

public class ErrorsMessageDemo {

    public static void main(String[] args) {

        User user = new User();
        user.setName("test");

        Errors errors = new BeanPropertyBindingResult(user, "user");
        errors.reject("user.properties.not.null");
        errors.rejectValue("name", "name.required");


        List<ObjectError> allErrors = errors.getAllErrors();

        MessageSource messageSource = createMessageSource();

        allErrors.forEach(e -> {
            String message = messageSource.getMessage(e.getCode(), e.getArguments(), Locale.getDefault());
            System.out.println(message);
        });



    }


    static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User所有属性不能为空");
        messageSource.addMessage("id.required", Locale.getDefault(), "the id of user must not be null");
        messageSource.addMessage("name.required", Locale.getDefault(), "the name of user must not be null");
        return messageSource;
    }

}
