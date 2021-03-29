
package model.arena;

import commons.Range;
import model.Unitpedia;
import model.players.Player;
import model.players.PlayerID;
import model.sprites.ElixirBar;
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
    private final List<Sprite> sprites = new ArrayList<>();
    private final List<Unit> units = new ArrayList<>();
    private final List<Sprite> obstacles = new ArrayList<>();
    private HandCardContainer handCard;
    private Player player;
    private final Unitpedia unitpedia;

    public Arena(Unitpedia unitpedia) {
        this.unitpedia = unitpedia;
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.handCard = new HandCardContainer(460, 600, player.getHandCard());
        addSprite(new ElixirBar(460, 580, player.getElixir()));
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
                //TODO: get the Unit type by card and Summon(clone) the unit, that is summon is not the handcardContainer method.
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
        player.getElixir().costElixir(unit.getEP());
    }

    public void removeSprite(Sprite sprite) {
        // Note: if ConcurrentModificationException may throw,
        // the sprites should use CopyOnWriteArrayList instead.
        sprites.remove(sprite);
    }
}
