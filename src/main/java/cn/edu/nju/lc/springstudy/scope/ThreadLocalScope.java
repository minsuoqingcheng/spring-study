package cn.edu.nju.lc.springstudy.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * 线程级别Scope
 */
public class ThreadLocalScope implements Scope {

    public static final String SCOPE_NAME = "thread-local-scope";

    private final NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal<Map<String, Object>>(SCOPE_NAME) {

        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        //维护的是线程里面的数据
        Map<String, Object> map = threadLocal.get();
        Object object = map.get(name);
        if (Objects.isNull(object)) {
            object = objectFactory.getObject();
            map.put(name, object);
        }
        return object;
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> map = threadLocal.get();
        return map.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        Map<String, Object> map = threadLocal.get();
        return map.get(key);
    }

    @Override
    public String getConversationId() {
        return Thread.currentThread().getId() + "";
    }
}
