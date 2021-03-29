package model.players;

import gameloop.GameLoop;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Elixir implements GameLoop.Listener {
    private int elixir;
    private final int generateRate;
    private int count = 0;

    public static Elixir setupElixir() {
        return new Elixir(10, 100);
    }

    public Elixir(int elixir, int generateRate) {
        this.elixir = elixir;
        this.generateRate = generateRate;
    }

    public void grainElixir(int elixir) {
        this.elixir += elixir;
    }

    public void costElixir(int loss) {
        this.elixir -= loss;
    }

    public int getElixir() {
        return elixir;
    }

    @Override
    public void onTick() {
        if (count++ == generateRate) {
            grainElixir(1);
            count = 0;
        }
    }
}
