package cn.edu.nju.lc.springstudy.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

public class ResourceUtil {

    public static String getContent(Resource resource) throws IOException {
        return getContent(resource, "UTF-8");
    }


    public static String getContent(Resource resource, String encoding) throws IOException {

        EncodedResource encodedResource = new EncodedResource(resource, encoding);

        try (Reader reader = encodedResource.getReader()) {
            return IOUtils.toString(reader);
        }
    }

}
