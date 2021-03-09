package model.players;


import model.Card;
import model.CardDeck;

import java.util.*;
import java.util.List;
import model.CardDeck.CardName;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class HandCard {
    private List<CardName> cards = new ArrayList<>();
    private final CardDeck cardDeck;
    private final Random random = new Random();
    private static final int SIZE = 4;

    public HandCard(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
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
