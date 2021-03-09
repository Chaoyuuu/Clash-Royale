
package model.arena;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import commons.Range;
import model.Unitpedia;
import model.players.HandCard;
import model.players.PlayerID;
import model.sprites.CardContainer;
import model.sprites.HandCardContainer;
import model.sprites.Sprite;
import model.sprites.unit.Unit;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Arena {
    private List<Sprite> sprites = new LinkedList<>();
    private HandCardContainer handCard;
    private Unitpedia unitpedia;

    public Arena(Unitpedia unitpedia) {
        this.unitpedia = unitpedia;
    }

    public void setHandCard(HandCard handCard) {
        this.handCard = new HandCardContainer(460, 600, handCard);
        addSprite(this.handCard);
    }

    public Collection<Sprite> getAllSprites() {
        return sprites;
    }

    public void click(Point point) {
        sprites.stream()
                .filter(s -> s.getBody().contains(point))
                .findAny()
                .ifPresentOrElse(s -> s.onClick(point), () -> handCard.summonUnit(point));
    }

    public Collection<Sprite> getSpritesByRange(Range range) {
        return Collections.emptySet(); // TODO
    }

    public Collection<Sprite> getSpritesByPlayerId(PlayerID playerID) {
        return sprites.stream()
                .filter(s -> s instanceof Unit)
                .map(s -> (Unit) s)
                .filter(u -> u.getPlayerID().equals(playerID))
                .collect(Collectors.toList());
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
        sprite.getArena(this);
    }

    public void summonUnitOnArena(Unitpedia.UnitName name, Point location) {
        Sprite sprite = unitpedia.getUnit(name);
        sprite.setPoint(location);
        addSprite(sprite);
    }

    public void removeSprite(Sprite sprite) {
        // Note: if ConcurrentModificationException may throw,
        // the sprites should use CopyOnWriteArrayList instead.
        sprites.remove(sprite);
    }
}
