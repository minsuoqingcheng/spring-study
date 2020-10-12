package cn.edu.nju.lc.springstudy.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;

public class GenericTypeResolverDemo {

    public static void main(String[] args) throws Exception {

        Method method = GenericTypeResolverDemo.class.getMethod("getString");

        Class<?> returnType = GenericTypeResolver.resolveReturnType(method, GenericTypeResolverDemo.class);
        System.out.println(returnType);

    }


    private static String getString() {
        return null;
    }

}
