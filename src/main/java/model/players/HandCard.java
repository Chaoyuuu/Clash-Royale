package model.players;


import model.Card;
import model.CardDeck;
import model.CardDeck.CardName;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static model.CardDeck.CardName.*;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class HandCard {
    private final List<CardName> cards = new ArrayList<>();
    private final CardDeck cardDeck;
    private final Random random = new Random();
    private int SIZE = 4;

    public HandCard(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    //for testing
    public HandCard(CardDeck cardDeck, int size) {
        this.cardDeck = cardDeck;
        cards.add(ADVENTURER_A);
        cards.add(ADVENTURER_B);
        this.SIZE = cards.size();
    }

    public Card getCardInRandom() {
        int index = random.nextInt(SIZE);
        CardName card = cards.get(index);
        return cardDeck.getCard(card);
    }

    public void reset() {
        cards.clear();
    }

    // TODO: better way to limit the cards' size in eight
    public void addCards(CardName card) {
        if (cards.size() < SIZE) {
            cards.add(card);
        }
    }

    public void removeCards(CardName card) {
        cards.remove(card);
    }

}
