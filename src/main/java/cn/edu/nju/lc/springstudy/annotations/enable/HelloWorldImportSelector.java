package cn.edu.nju.lc.springstudy.annotations.enable;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class HelloWorldImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"cn.edu.nju.lc.springstudy.annotations.enable.HelloWorldConfiguration"};
    }
}
