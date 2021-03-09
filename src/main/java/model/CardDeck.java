package model;

import java.util.HashMap;
import java.util.Map;

import static model.Card.createCard;
import static model.CardDeck.CardName.*;
import static model.Unitpedia.UnitName.ADVENTURER_IDLE;
import static model.Unitpedia.UnitName.ADVENTURER_U;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class CardDeck {
    private final Map<CardName, Card> cards = new HashMap<>();

    public CardDeck() {
        setupCard();
    }

    private void setupCard() {
        cards.put(ADVENTURER_C, createCard(ADVENTURER_U, "w.png"));
        cards.put(ADVENTURER_A, createCard(ADVENTURER_U, "w.png"));
        cards.put(ADVENTURER_B, createCard(ADVENTURER_IDLE, "g.png"));
        cards.put(ADVENTURER_D, createCard(ADVENTURER_IDLE, "g.png"));
    }

    public Card getCard(CardName name) {
        return cards.get(name).clone();
    }

    public enum CardName {
        ADVENTURER_C, DRAGON_C,
        ADVENTURER_A,
        ADVENTURER_B,
        ADVENTURER_D,
    }

}
