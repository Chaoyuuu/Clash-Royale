package model.sprites.unit;

import model.players.PlayerID;
import model.sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class WalkingUnit extends Unit {
    private List<Sprite> obstacles = new ArrayList<>();

    public WalkingUnit(Rectangle body, int HP, int AP, int AD, int offset, PlayerID playerID, List<Sprite> obstacles) {
        super(body, HP, AP, AD, offset, playerID);
        this.obstacles = obstacles;
    }

    public WalkingUnit(Rectangle body, int HP, int AP, int AD, int offset, Rectangle attackRange, PlayerID playerID, List<Sprite> obstacles) {
        super(body, HP, AP, AD, attackRange, offset, playerID);
        this.obstacles = obstacles;
    }

    @Override
    public void move() {
        System.out.println("Move");
        angle.translate(offset, this);
    }
}
