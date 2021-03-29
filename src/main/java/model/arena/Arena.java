
package model.arena;

import commons.Range;
import model.Unitpedia;
import model.players.HandCard;
import model.players.PlayerID;
import model.sprites.HandCardContainer;
import model.sprites.Sprite;
import model.sprites.unit.Unit;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Arena {
    private List<Sprite> sprites = new ArrayList<>();
    private List<Unit> units = new ArrayList<>();
    private List<Sprite> obstacles = new ArrayList<>();
    private HandCardContainer handCard;
    private final Unitpedia unitpedia;

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

    public void attack(Unit attackUnit) {
        units.stream()
                .filter(s -> s.getBody().intersects(attackUnit.getAttackRange()) && s != attackUnit)
                .forEach(s -> s.damageHP(attackUnit.getAP()));
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
        sprite.getArena(this);
        if (sprite instanceof Unit) {
            addUnit((Unit) sprite);
        }
    }

    private void addUnit(Unit unit) {
        units.add(unit);
    }

    public void summonUnitOnArena(Unitpedia.UnitName name, Point location) {
        Unit unit = unitpedia.getUnit(name);
        unit.setLocation(location);
        addSprite(unit);
    }

    public void removeSprite(Sprite sprite) {
        // Note: if ConcurrentModificationException may throw,
        // the sprites should use CopyOnWriteArrayList instead.
        sprites.remove(sprite);
    }
}
