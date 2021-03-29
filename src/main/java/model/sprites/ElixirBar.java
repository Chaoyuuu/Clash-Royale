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

    // TODO: get the elixir from the player directly
    public Elixir getElixir() {
        return elixir;
    }

    @Override
    public void onClick(Point location) {
        // nothing to do in current version
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(body.x, body.y, body.width, body.height);

        g.fillRect(body.x, body.y, elixir.getElixir() * 50, body.height);
    }

    @Override
    protected void onSetupFSM(FSM<State> fsm) {
        fsm.setInitialState(STATIC);
        fsm.put(STATIC,
                outerState(100, innerState(this, "wood.jpg")));
    }
}
