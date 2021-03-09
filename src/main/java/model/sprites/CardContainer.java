package model.sprites;

import fsm.FSM;
import model.Card;
import model.Unitpedia.UnitName;

import java.awt.*;

import static fsm.FSM.EOS;
import static fsm.InnerState.innerState;
import static fsm.OuterState.outerState;
import static model.sprites.CardContainer.Event.*;
import static model.sprites.State.*;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class CardContainer extends Sprite {
    private Card card;
    private Color color = Color.white;

    public CardContainer(int x, int y, Card card) {
        super(new Rectangle(new Point(x, y), new Dimension(100, 130)));
        this.card = card;
        setupFSM();
    }

    public UnitName summon() {
        fsm.trigger(SUMMON_EVENT);
        return card.summonUnit();
    }

    public void getCard(Card card) {
        this.card = card;
        fsm.trigger(GETCARD_EVENT);
    }

    public void unselect() {
        fsm.trigger(UNSELECT_EVENT);
    }

    public void select() {
        fsm.trigger(SELECT_EVENT);
    }

    @Override
    public void onClick(Point location) {
        switch (getState()) {
            case SELECTED:
                unselect();
                break;
            case GAME:
                select();
                break;
        }
    }

    @Override
    public void update() {
        if (getState() == SELECTED) {
            color = Color.PINK;
        } else {
            color = Color.WHITE;
        }
    }

    @Override
    public void render(Graphics g) {
        fsm.update();
        g.setColor(this.color);
        g.fillRect(body.x, body.y, body.width, body.height);

        Image image = fsm.getImage();
        g.drawImage(image, body.x, body.y, body.width, body.height, null);
    }

    @Override
    protected void onSetupFSM(FSM<State> fsm) {
        fsm.setInitialState(GAME);
    }

    private void setupFSM() {
        Image image = card.getImage();
        fsm.put(WAITING, outerState(100, innerState(this, image)));

        fsm.put(GAME, outerState(100, innerState(this, image)));

        fsm.put(SELECTED, outerState(100, innerState(this, image)));

        fsm.put(REMOVE, outerState(100, innerState(this, image)));

        fsm.put(EMPTY, outerState(100, innerState(this, image)));

        fsm.addTransition(WAITING, EOS, GAME);
        fsm.addTransition(GAME, SELECT_EVENT, SELECTED);
        fsm.addTransition(SELECTED, UNSELECT_EVENT, GAME);
        fsm.addTransition(SELECTED, SUMMON_EVENT, REMOVE);
        fsm.addTransition(REMOVE, EOS, EMPTY);
        fsm.addTransition(EMPTY, GETCARD_EVENT, WAITING);
    }

    public enum Event {
        SUMMON_EVENT, GETCARD_EVENT, UNSELECT_EVENT, SELECT_EVENT
    }
}
