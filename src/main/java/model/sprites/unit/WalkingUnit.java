package model.sprites.unit;

import model.players.PlayerID;
import model.sprites.Sprite;

import java.awt.*;
import java.util.List;

public abstract class WalkingUnit extends Unit {
    private List<Sprite> obstacles;

    public WalkingUnit(Rectangle imageRange, Rectangle body, Rectangle attackRange,
                       int HP, int AP, int AD, int offset, PlayerID id, List<Sprite> obstacles) {
        super(imageRange, body, attackRange, HP, AP, AD, offset, id);
        this.obstacles = obstacles;
    }

    @Override
    public void move() {
        angle.moveForward(offset, this);
    }
}
