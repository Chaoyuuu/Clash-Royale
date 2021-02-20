
package model.arena;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import commons.Range;
import model.players.PlayerID;
import model.sprites.Sprite;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Arena {
    private Set<Sprite> sprites = new HashSet<>();

    public Collection<Sprite> getAllSprites() {
        return sprites;
    }

    public Collection<Sprite> getSpritesByRange(Range range) {
        return Collections.emptySet(); // TODO
    }

    public Collection<Sprite> getSpritesByPlayerId(PlayerID playerID) {
        return null; // TODO
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
        sprite.getArena(this);
    }

    public void removeSprite(Sprite sprite) {
        // Note: if ConcurrentModificationException may throw,
        // the sprites should use CopyOnWriteArrayList instead.
        sprites.remove(sprite);
    }

}
