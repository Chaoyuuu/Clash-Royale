package model.sprites.unit;

import java.awt.*;

public abstract class Movement {
    private Unit unit;
    public void updateAngle(Angle angle) {

    }

    public abstract void move();
    protected abstract Graph generateGraph(Point invalidPosition);
}
