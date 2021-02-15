package model.arena;

import model.players.PlayerID;
import model.sprites.Sprite;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import commons.Range;

public class Arena {
    private Set<Sprite> sprites = new HashSet<>();

    public Collection<Sprite> getAllSprites() {
        return sprites;
    }

    public Collection<Sprite> getSpritesByRange(Range range) {
        return Collections.emptySet(); // TODO
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void removeSprite(Sprite sprite) {
        // Note: if ConcurrentModificationException may throw,
        // the sprites should use CopyOnWriteArrayList instead.
        sprites.remove(sprite);
    }

    public Collection<Sprite> getSpritesByPlayerId(PlayerID playerID) {
        return null; // TODO
    }

}
