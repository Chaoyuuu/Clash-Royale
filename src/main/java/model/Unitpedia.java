package model;

import model.sprites.unit.Adventurer;
import model.sprites.unit.Unit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Unitpedia {
    private Map<UnitName, Unit> units = new HashMap<>();

    public Unitpedia() {
        setup();
    }

    private void setup() {
        units.put(UnitName.ADVENTURER_U, new Adventurer());
    }

    public Unit getUnit(UnitName name) {
        return units.get(name).clone();
    }

    public enum UnitName {
        ADVENTURER_U, DRAGON_U
    }

    public static void main(String[] args) {
        Unitpedia unitpedia = new Unitpedia();
        Unit unit = unitpedia.getUnit(UnitName.ADVENTURER_U);
        System.out.println("A");
    }
}
