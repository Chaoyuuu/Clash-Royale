package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static utils.ResourceUtils.getResourceURL;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class ImageUtils {

    public static Image getImage(String s) {
        return new ImageIcon(getResourceURL(s)).getImage();
    }

    public static Image getImage(File file) throws IOException {
        return ImageIO.read(file);
    }
}
