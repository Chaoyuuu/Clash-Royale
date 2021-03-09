package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import static utils.ResourceUtils.getResourceURL;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class ImageUtils {

    public static Image getImage(String s) {
        Image image = new ImageIcon(getResourceURL(s)).getImage();
        return image;
    }

    public static Image getImage(File file) throws IOException {
        return ImageIO.read(file);
    }

    public static void main(String[] args) {
        Image image = getImage("adventurer/adventurer-attack1-00.png");
        Image image1 = getImage("c.png");

        System.out.println("s");
    }
}
