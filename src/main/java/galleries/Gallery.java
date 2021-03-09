package galleries;

import java.awt.*;
import java.util.List;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */

public interface Gallery {
    /**
     * @param pic the picture's number
     * @return is the pic within the gallery's picture range
     */
    default boolean containsPicture(int pic) {
        return getPictureRange().within(pic);
    }

    /**
     * @param pic the picture's number
     * @return the #pic image
     */
    Image getImageByPic(int pic);

    List<Image> getImages();

    /**
     * @return the Range of the picture number
     */
    Range getPictureRange();
}
