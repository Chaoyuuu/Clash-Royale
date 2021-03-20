package model.sprites.unit;

import model.players.PlayerID;
import model.sprites.Sprite;

import java.awt.*;

public abstract class Unit extends Sprite {
    protected int HP;
    protected int AP;
    protected int AD;
    protected Rectangle attackRange;
    protected int offset;
    protected Angle angle;
    protected PlayerID playerID;

    public Unit(Rectangle body, int HP, int AP, int AD, Rectangle attackRange, int offset, PlayerID playerID) {
        super(body);
        this.HP = HP;
        this.AP = AP;
        this.AD = AD;
        this.attackRange = attackRange;
        this.offset = offset;
        setAngle(playerID);
    }

    public Unit(Rectangle body, int HP, int AP, int AD, int offset, PlayerID playerID) {
        super(body);
        this.HP = HP;
        this.AP = AP;
        this.AD = AD;
        this.attackRange = new Rectangle(0, 0);
        this.offset = offset;
        setAngle(playerID);
    }

    private void setAngle(PlayerID id) {
        if (id == PlayerID.PLAYER_A) {
            this.angle = new Angle(90);
        } else {
            this.angle = new Angle(270);
        }
    }

    public Rectangle getAttackRange() {
        return attackRange;
    }

    public int getAP() {
        return AP;
    }

    public void damageHP(int damageHP) {
        HP -= damageHP;
    }

    public abstract void move();

    public abstract void attack();

    public Unit clone() {
        Unit copy = (Unit) super.clone();
        copy.angle = this.angle.clone();
        return copy;
    }

    public PlayerID getPlayerID() {
        return playerID;
    }
}
