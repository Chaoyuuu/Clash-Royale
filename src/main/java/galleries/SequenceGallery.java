package galleries;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static utils.ResourceUtils.getResourceURL;

/**
 * The sequential gallery, it requires the resource homePath where the images are located.
 * The sequence will contain all the images in the homePath directory, it expects all the image file names
 * are in number form, (e.g. 0.png, 1.png, 2.png,...), so that it can determine the sequence order (ascending).
 * @author johnny850807 (waterball)
 */
public class SequenceGallery implements Gallery {
    public static final String REGEX_SUPPORTED_FILE_NAME_PATTERN = "([1-9][0-9]*|0)\\.(bmp|jpg|png|gif)";
    private List<Image> imageSequence;
    private Range pictureRange;

    public SequenceGallery(String folderPath, Range pictureRange) {
        this.imageSequence = readImageSequenceFromFolderPath(folderPath);
        this.pictureRange = pictureRange;
    }

    public SequenceGallery(List<Image> imageSequence, Range pictureRange) {
        this.imageSequence = imageSequence;
        this.pictureRange = pictureRange;
    }

    @Override
    public Image getImageByPic(int pic) {
        return imageSequence.get(pic);
    }

    @Override
    public List<Image> getImages() {
        return imageSequence;
    }

    private List<Image> readImageSequenceFromFolderPath(String folderPath) {
        try {
            List<File> files = Files.list(Path.of(getResourceURL(folderPath).toURI()))
                    .map(this::readFile)
                    .filter(this::validateFileName)
                    .collect(Collectors.toList());
            sortFilesByAscendingIndex(files);
            return files.stream().map(this::readImage).collect(Collectors.toList());
        } catch (URISyntaxException | IOException e ) {
            throw new IllegalArgumentException("readImageSequenceFromHomePath");
        }
    }

    private File readFile(Path filePath) {
        return filePath.toFile();
    }

    private Image readImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateFileName(File file) {
        String fileName = file.getName();
        return Pattern.matches(REGEX_SUPPORTED_FILE_NAME_PATTERN, fileName);
    }

    private void sortFilesByAscendingIndex(List<File> picFiles) {
        picFiles.sort((f1, f2) -> {
            int f1Index = Integer.parseInt(f1.getName().split("\\.")[0]);
            int f2Index = Integer.parseInt(f2.getName().split("\\.")[0]);
            return f1Index - f2Index;
        });
    }

    @Override
    public Range getPictureRange() {
        return pictureRange;
    }

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        String pathname = "adventurer/idle2";
        Gallery gallery = new SequenceGallery(pathname, new Range(0, 4));

        JFrame jf = new JFrame("");
        JLabel jLabel = new JLabel();
        JScrollPane scrollPane = new JScrollPane(jLabel);
        jLabel.setIcon(new ImageIcon(gallery.getImageByPic(2)));

        jf.getContentPane().add(scrollPane);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

        new Thread(() -> {
            List<Image> images = gallery.getImages();
            try {
                while(true)
                {
                    for (Image image:images) {
                        Thread.sleep(200);
                        jLabel.setIcon(new ImageIcon(image));
                    }
                }
            } catch (InterruptedException ignored) {
            }
        }).start();
    }
}
