package model.players;

import model.CardDeck.CardName;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Player {
    private final Elixir elixir;
    private final HandCard handCard;

    public Player(Elixir elixir, HandCard handCard) {
        this.elixir = elixir;
        this.handCard = handCard;
    }

    public Elixir getElixir() {
        return elixir;
    }

    public HandCard getHandCard() {
        return handCard;
    }

    public void chooseCard(CardName card) {
        handCard.addCards(card);
    }
}
