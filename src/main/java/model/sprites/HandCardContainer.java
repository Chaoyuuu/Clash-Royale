package model.sprites;

import fsm.FSM;
import model.Card;
import model.Unitpedia;
import model.players.HandCard;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static fsm.InnerState.innerState;
import static fsm.OuterState.outerState;
import static model.sprites.State.*;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class HandCardContainer extends Sprite {
    private final List<CardContainer> cards = new LinkedList<>();
    private final HandCard handCard;
    private CardContainer selectedCard;

    public HandCardContainer(int x, int y, HandCard handCard) {
        super(new Rectangle(new Point(x, y), new Dimension(540, 170)));
        this.handCard = handCard;
        setupCards();
    }

    private void setupCards() {
        for (int i = 0; i < 2; i++) {
            cards.add(new CardContainer(20 + 130 * i + body.x, 20 + body.y, dealCard()));
        }
    }

    private Card dealCard() {
        return handCard.getCardInRandom();
    }

    public void summonUnit(Point location) {
        if (selectedCard != null) {
            arena.summonUnitOnArena(selectedCard.summon(), location);
            selectedCard = null;
        }
    }

    @Override
    public void onClick(Point location) {
        cards.forEach( c -> {
                    if (c.getBody().contains(location)) {
                        c.onClick(location);
                        updateSelectedCard(c);
                    }});
    }

    private void updateSelectedCard(CardContainer card) {
        if (card.getState() == SELECTED) {
            if (selectedCard != null) {
                selectedCard.unselect();
            }
            selectedCard = card;
        } else if (card.getState() == SELETABLE) {
            selectedCard = null;
        }
    }

    // TODO: when to getCard
    @Override
    public void update() {
        cards.forEach(c -> {
            c.update();
            if (c.getState() == REMOVE) {
                c.getCard(dealCard());
            }
        });
    }

    @Override
    public void render(Graphics g) {
        fsm.update();
        Image image = fsm.getImage();
        g.drawImage(image, body.x, body.y, body.width, body.height, null);
        cards.forEach(c -> c.render(g));
    }

    @Override
    protected void onSetupFSM(FSM<State> fsm) {
        fsm.setInitialState(STATIC);
        fsm.put(STATIC,
                outerState(100, innerState(this, "wood.jpg")));
    }
}
