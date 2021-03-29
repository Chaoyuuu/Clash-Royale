package model.sprites;

import fsm.FSM;
import model.players.Elixir;

import java.awt.*;

import static fsm.InnerState.innerState;
import static fsm.OuterState.outerState;
import static model.sprites.State.STATIC;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class ElixirBar extends Sprite {
    private final Elixir elixir;

    public ElixirBar(int x, int y, Elixir elixir) {
        super(new Rectangle(new Point(x, y), new Dimension(500, 10)),
                new Rectangle(new Point(x, y), new Dimension(500, 10)));
        this.elixir = elixir;
    }

    @Override
    public void onClick(Point location) {
        // nothing to do in the current version
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        int elixirValue = elixir.getElixir();
        g.setColor(Color.WHITE);
        g.drawRect(body.x, body.y, body.width, body.height);
        g.fillRect(body.x, body.y,  elixirValue * 50, body.height);
        g.drawString(String.valueOf(elixirValue), body.x, body.y - 5);
    }

    @Override
    protected void onSetupFSM(FSM<State> fsm) {
        fsm.setInitialState(STATIC);
        fsm.put(STATIC,
                outerState(100, innerState(this, "wood.jpg")));
    }
}
