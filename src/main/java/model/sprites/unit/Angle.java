package model.sprites.unit;

import exception.CloneException;

import static java.lang.Math.*;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Angle implements Cloneable {
    /**
     * (right) angle: 0
     * (left) angle: 180
     * (top) angle: 90
     * (tail) angle: 270
     */

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

    public void moveForward(int offset, Unit unit) {
        double radians = toRadians(angle);
        int offsetX = (int) (cos(radians) * offset);
        int offsetY = (int) (sin(radians) * offset);
        unit.getImageRange().translate(offsetX, offsetY);
    }

    public Angle clone() {
        try {
            return (Angle) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneException(e);
        }
    }
}
