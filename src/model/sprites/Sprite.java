package model.sprites;

import java.awt.*;

public abstract class Sprite {
    private Rectangle range;
    private Rectangle body;

    public abstract void update();
    public abstract void render(Graphics p);
}
