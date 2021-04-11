package model.players;

import gameloop.GameLoop;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Elixir implements GameLoop.Listener {
    private static final int MAX_ELIXIR = 10;
    private static final int GENERATE_RATE = 100;
    private int elixir;
    private int count = 0;

    public Elixir() {
        this.elixir = MAX_ELIXIR;
    }

    public void grainElixir(int elixir) {
        this.elixir += elixir;
        if (this.elixir > MAX_ELIXIR) {
            this.elixir = MAX_ELIXIR;
        }
    }

    public void costElixir(int loss) {
        this.elixir -= loss;
        if (this.elixir < 0) {
            this.elixir = 0;
        }
    }

    public int getElixir() {
        return elixir;
    }

    @Override
    public void onTick() {
        if (count++ == GENERATE_RATE) {
            grainElixir(1);
            count = 0;
        }
    }
}
