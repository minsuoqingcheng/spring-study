package cn.edu.nju.lc.springstudy.annotations.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EvenConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //条件上下文
        Environment environment = context.getEnvironment();
        return environment.acceptsProfiles("even");
    }
}
