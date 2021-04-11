package gameloop;

import model.arena.Arena;
import model.sprites.Sprite;
import view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static utils.DelayUtils.delay;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class GameLoop {
    private final View view;
    private final Arena arena;
    private boolean running;
    private Collection<Sprite> sprites;
    private final List<Listener> listeners = new ArrayList<>();

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
            listeners.forEach(Listener::onTick);
        }
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void start() {
        running = true;
        loop();
    }

    public void stop() {
        running = false;
    }

    public interface Listener {
        void onTick();
    }
}
