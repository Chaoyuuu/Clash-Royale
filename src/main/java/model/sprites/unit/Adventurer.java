package model.sprites.unit;

import fsm.FSM;
import galleries.Gallery;
import galleries.Range;
import galleries.SequenceGallery;
import model.players.PlayerID;
import model.sprites.State;

import java.awt.*;
import java.util.Collections;

import static fsm.FSM.EOS;
import static fsm.InnerState.innerState;
import static fsm.OuterState.outerState;
import static fsm.action.Attack.attackAct;
import static fsm.action.DefaultAction.defaultAct;
import static fsm.action.Move.moveAct;
import static model.sprites.State.*;
import static model.sprites.unit.Adventurer.Event.ATTACK_EVENT;
import static utils.GraphicUtils.drawBoxesAndHP;
import static utils.GraphicUtils.drawImage;
import static utils.LocationUtils.updateLocationByPlayerId;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Adventurer extends WalkingUnit {

    public Adventurer() {
        super(new Rectangle(0, 0, 100, 80),
                new Rectangle(25, 15, 45, 65),
                new Rectangle(60, 10, 40, 70),
                100, 100, 100, 5, PlayerID.PLAYER_B,
                Collections.emptyList());
    }

    @Override
    protected void onSetupFSM(FSM<State> fsm) {
        fsm.setInitialState(MOVING);

        Gallery runGallery = new SequenceGallery("adventurer/run", new Range(0, 6));
        Gallery attackGallery = new SequenceGallery("adventurer/attack2", new Range(0, 6));
        Gallery idleGallery = new SequenceGallery("adventurer/idle2", new Range(0, 4));
        Gallery dieGallery = new SequenceGallery("adventurer/die", new Range(0, 7));

        fsm.put(ATTACK,
                outerState(10,
                        innerState(this, attackGallery.getImageByPic(0), defaultAct()),
                        innerState(this, attackGallery.getImageByPic(1), defaultAct()),
                        innerState(this, attackGallery.getImageByPic(2), defaultAct()),
                        innerState(this, attackGallery.getImageByPic(3), attackAct()),
                        innerState(this, attackGallery.getImageByPic(4), defaultAct()),
                        innerState(this, attackGallery.getImageByPic(5), defaultAct())));

        fsm.put(MOVING, outerState(10, this, runGallery.getImages(), moveAct()));
        fsm.put(IDLE, outerState(10, this, idleGallery.getImages()));
        fsm.put(DIE, outerState(10, this, dieGallery.getImages()));

        fsm.addTransition(IDLE, ATTACK_EVENT, ATTACK);
        fsm.addTransition(ATTACK, EOS, IDLE);
        fsm.addTransition(MOVING, ATTACK_EVENT, ATTACK);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        fsm.update();
        drawImage(fsm.getImage(), this, g);
        updateLocationByPlayerId(body, imageRange, bodyOffset, playerID);
        updateLocationByPlayerId(attackRange, imageRange, attackOffset, playerID);
        drawBoxesAndHP(this, g);
    }

    @Override
    public void attack() {
        arena.attack(this);
    }

    @Override
    public void onClick(Point location) {
        fsm.trigger(ATTACK_EVENT);
    }

    public enum Event {
        ATTACK_EVENT
    }
}
