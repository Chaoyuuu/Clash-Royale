package model.sprites.unit;

import model.players.PlayerID;
import model.sprites.Sprite;

import java.awt.*;

public abstract class Unit extends Sprite {
    protected int HP;
    protected int AP;
    protected int AD;
    protected int offset;
    protected Angle angle;
    protected PlayerID playerID;

    public Unit(Rectangle body, int HP, int AP, int AD, int offset, PlayerID playerID) {
        super(body);
        this.HP = HP;
        this.AP = AP;
        this.AD = AD;
        this.offset = offset;
        if (playerID == PlayerID.PLAYER_A) {
            this.angle = new Angle(90);
        } else {
            this.angle = new Angle(270);
        }

    }

    public void damageHP(int damageHP) {
        HP -= damageHP;
    }

    public abstract void move();

    public abstract void attack();

}
