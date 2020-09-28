package cn.edu.nju.lc.springstudy.validation;


import cn.edu.nju.lc.springstudy.ioc.overview.dependency.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.*;

import java.util.List;
import java.util.Locale;

public class ValidatorDemo {

    public static void main(String[] args) {
        Validator userValidator = new UserValidator();
        //判断是否支持目标对象
        User user = new User();
        if (userValidator.supports(user.getClass())) {
            Errors errors = new BeanPropertyBindingResult(user, "user");
            userValidator.validate(user, errors);
            MessageSource messageSource = ErrorsMessageDemo.createMessageSource();

            List<ObjectError> allErrors = errors.getAllErrors();

            allErrors.forEach(e -> {
                String message = messageSource.getMessage(e.getCode(), e.getArguments(), Locale.getDefault());
                System.out.println(message);
            });

        }
    }


    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        }
    }

}
