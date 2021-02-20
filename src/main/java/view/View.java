package view;

import model.sprites.Sprite;

import java.util.Collection;

public interface View {
    void repaint(Collection<Sprite> sprites);
}
