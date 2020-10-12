package cn.edu.nju.lc.springstudy.conversion;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesToStringConverter implements ConditionalGenericConverter {

    @Override
    public boolean matches(TypeDescriptor source, TypeDescriptor target) {
        return Properties.class.equals(source.getObjectType()) &&
                String.class.equals(target.getObjectType());
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Properties.class, String.class));
    }

    @Override
    public Object convert(Object o, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor1) {
        Properties properties = (Properties) o;
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue());
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
