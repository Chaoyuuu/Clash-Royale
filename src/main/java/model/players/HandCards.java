package model.players;

import model.sprites.Card;

import java.awt.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class HandCards {
    private final Set<Card> cards = new HashSet<>();
    private boolean chooseCard = false;
    private Card selectedCard;

    public void selectCard(Point point) {
        if (chooseCard) {
            selectedCard.summonUnit(point);
            chooseCard = false;
        } else if (getCardByPoint(point).isPresent()) {
            System.out.println("Copy !!");
            selectedCard = getCardByPoint(point).get();
            chooseCard = true;
        }
    }

    public Optional<Card> getCardByPoint(Point point) {
        for (Card card:cards) {
            if (card.getBody().contains(point)) {
                return Optional.of(card);
            }
        }
        return Optional.empty();
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

}
