package model.sprites.unit;

import model.sprites.Sprite;

import static java.lang.Math.*;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Angle {
    private int angle;

    public Angle(int angle) {
        setAngle(angle);
    }

    public void setAngle(int angle) {
        this.angle = angle % 360;
    }

    public int getAngle() {
        return angle;
    }

    public void translate(int offset, Sprite sprite) {
        double radians = toRadians(angle - 90);  // turn the axis for -90,
        int offsetX = (int)(cos(radians) * offset);
        int offsetY = (int)(sin(radians) * offset * -1);
        sprite.getBody().translate(offsetX, offsetY);
    }
}
