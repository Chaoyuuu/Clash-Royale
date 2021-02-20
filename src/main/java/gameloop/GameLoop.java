package gameloop;

import model.arena.Arena;
import model.sprites.Sprite;
import view.View;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static utils.DelayUtils.delay;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class GameLoop {
    private View view;
    private Boolean running;
    private Arena arena;
    private Collection<Sprite> sprites;

    public GameLoop(View view, Arena arena) {
        this.view = view;
        this.arena = arena;
    }

    public void loop() {
        while (running) {
            delay(15);
            sprites = arena.getAllSprites();
            sprites.forEach(Sprite::update);
            view.repaint(sprites);
        }
    }

    public void start() {
        running = true;
        loop();
    }

    public void stop() {
        running = false;
    }
}
