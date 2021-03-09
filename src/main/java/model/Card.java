package model;

import exception.CloneException;
import model.Unitpedia.UnitName;
import utils.ImageUtils;

import java.awt.*;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */

public class Card implements Cloneable {

    private final UnitName unit;

    private Image image;

    public static Card createCard(UnitName unit, String imagePath) {
        Image image = ImageUtils.getImage(imagePath);
        return new Card(unit, image);
    }

    private Card(UnitName unit, Image image) {
        this.unit = unit;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public UnitName summonUnit() {
        return unit;
    }

    public Card clone() {
        try {
            return (Card) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneException(e);
        }
    }

}
