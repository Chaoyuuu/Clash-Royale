package model.sprites;

import exception.CloneException;
import fsm.FSM;
import model.OnMouseClickListener;
import model.arena.Arena;

import java.awt.*;

public abstract class Sprite implements OnMouseClickListener, Cloneable {
    protected Rectangle imageRange;
    protected Point bodyOffset;
    protected Rectangle body;
    protected FSM<State> fsm = new FSM<>();
    protected Arena arena;

    public Sprite(Rectangle imageRange, Rectangle body) {
        this.imageRange = imageRange;
        this.body = body;
        this.bodyOffset = new Point(body.getLocation());
        onSetupFSM(fsm);
        validateFSM();
    }

    private void validateFSM() {
        if (fsm.getInitialState() == null) {
            throw new IllegalStateException("The FSM must have the initial state.");
        }
    }

    public abstract void update();

    public abstract void render(Graphics g);

    protected abstract void onSetupFSM(FSM<State> fsm);

    protected State getState() {
        return fsm.getState();
    }

    public void getArena(Arena arena) {
        this.arena = arena;
    }

    public Rectangle getBody() {
        return body;
    }

    public Rectangle getImageRange() {
        return imageRange;
    }

    public Point getPoint() {
        return body.getLocation();
    }

    public void setLocation(Point point) {
        body.setLocation(point.x - body.width / 2, point.y - body.height / 2 - 15);
        imageRange.setLocation(body.x - bodyOffset.x, body.y - bodyOffset.y);
    }

    public Sprite clone() {
        try {
            Sprite copy = (Sprite) super.clone();
            copy.fsm = this.fsm.clone(copy);
            copy.imageRange = new Rectangle(-1, -1, imageRange.width, imageRange.height);
            copy.body = new Rectangle(body);
            copy.bodyOffset = bodyOffset;
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new CloneException(e);
        }
    }
}
