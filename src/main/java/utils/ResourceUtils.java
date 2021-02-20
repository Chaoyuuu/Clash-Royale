package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

import static java.lang.String.format;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class ResourceUtils {

    public static InputStream getResource(String resourcePath) {
        InputStream stream = ResourceUtils.class.getClassLoader().getResourceAsStream(resourcePath);
        Objects.requireNonNull(stream, format("Resource not found: %s", resourcePath));
        return stream;
    }

    public static URL getResourceURL(String resourcePath) {
        URL url = ResourceUtils.class.getClassLoader().getResource(resourcePath);
        Objects.requireNonNull(url, format("Resource not found: %s", resourcePath));
        return url;
    }

    public static File getResourceFile(String resourcePath) {
        URL resource = ResourceUtils.class.getClassLoader().getResource(resourcePath);
        Objects.requireNonNull(resource, format("Resource not found: %s", resourcePath));
        return new File(resource.getFile());
    }

    public static void main(String[] args) throws IOException {
        InputStream in = ResourceUtils.getResource("adventurer/adventurer-attack2-00.png");
        byte[] bytes = in.readAllBytes();
        System.out.println("Content length: " + bytes.length);

//        File file = ResourceUtils.getResourceFile("audio/error.wav");
//        System.out.println(file.getName());
    }
}