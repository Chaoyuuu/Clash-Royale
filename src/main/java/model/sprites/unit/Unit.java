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
    protected Rectangle attackRange;
    protected Point attackOffset;

    public Unit(Rectangle imageRange, Rectangle body, Rectangle attackRange,
                int HP, int AP, int AD, int offset, PlayerID id) {
        super(imageRange, body);
        this.HP = HP;
        this.AP = AP;
        this.AD = AD;
        this.attackRange = attackRange;
        this.attackOffset = new Point(attackRange.getLocation());
        this.offset = offset;
        this.playerID = id;
        setAngle(id);
    }

    private void setAngle(PlayerID id) {
        if (id == PlayerID.PLAYER_A) {
            this.angle = new Angle(180);
        } else {
            this.angle = new Angle(0);
        }
    }

    public Rectangle getAttackRange() {
        return attackRange;
    }

    public int getAP() {
        return AP;
    }

    public int getHP() {
        return HP;
    }

    public void damageHP(int damageHP) {
        HP -= damageHP;
    }

    public PlayerID getPlayerID() {
        return playerID;
    }

    public void setPlayerID(PlayerID playerID) {
        this.playerID = playerID;
    }

    public abstract void move();

    public abstract void attack();

    public Unit clone() {
        Unit copy = (Unit) super.clone();
        copy.angle = this.angle.clone();
        copy.attackRange = new Rectangle(this.attackRange);
        copy.attackOffset = this.attackOffset;
        return copy;
    }
}
