package model.sprites;

import exception.CloneException;
import fsm.FSM;
import model.OnMouseClickListener;
import model.arena.Arena;

import java.awt.*;

public abstract class Sprite implements OnMouseClickListener, Cloneable {
    protected Rectangle body;
    protected FSM<State> fsm = new FSM<>();
    protected Arena arena;

    public Sprite(Rectangle body) {
        this.body = body;
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

    public Point getPoint() {
        return body.getLocation();
    }

    public void setPoint(Point point) {
        body.setLocation(point);
    }

    public Sprite clone() {
        try {
            Sprite copy = (Sprite) super.clone();
            copy.fsm = this.fsm.clone(copy);
            copy.body = new Rectangle(-1, -1, body.width, body.height);
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new CloneException(e);
        }

    }
}
