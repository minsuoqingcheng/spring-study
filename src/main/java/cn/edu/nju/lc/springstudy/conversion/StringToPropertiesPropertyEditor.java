package cn.edu.nju.lc.springstudy.conversion;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

public class StringToPropertiesPropertyEditor extends PropertyEditorSupport {

    //1、实现setAsText方法
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        //2、将String对象转换成Properties对象
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }

        //3、临时存储Properties对象
        setValue(properties);
    }

    @Override
    public String getAsText() {
        Properties properties = (Properties) getValue();
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue());
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}

