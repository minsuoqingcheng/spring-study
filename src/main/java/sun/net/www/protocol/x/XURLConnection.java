package sun.net.www.protocol.x;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class XURLConnection extends URLConnection {

    private final ClassPathResource urlResource;

    /**
     * Constructs a URL connection to the specified URL. A connection to
     * the object referenced by the URL is not created.
     *
     * @param url the specified URL.
     */
    //url -> x:///META-INF/*.properties
    protected XURLConnection(URL url) {
        super(url);
        this.urlResource = new ClassPathResource(url.getPath());
    }

    @Override
    public void connect() throws IOException {

    }


    @Override
    public InputStream getInputStream() throws IOException {
        return urlResource.getInputStream();
    }
}
