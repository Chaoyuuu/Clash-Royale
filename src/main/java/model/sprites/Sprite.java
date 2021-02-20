package model.sprites;

import fsm.FSM;
import model.arena.Arena;

import java.awt.*;

public abstract class Sprite {
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
}
