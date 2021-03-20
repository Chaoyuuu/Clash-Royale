package model.sprites.unit;

import fsm.FSM;
import galleries.Gallery;
import galleries.Range;
import galleries.SequenceGallery;
import model.players.PlayerID;
import model.sprites.Sprite;
import model.sprites.State;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

import static fsm.InnerState.innerState;
import static fsm.OuterState.outerState;
import static fsm.action.Attack.attackAct;
import static fsm.action.DefaultAction.defaultAct;
import static fsm.action.Move.moveAct;
import static model.sprites.State.*;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Adventurer extends WalkingUnit {

    public Adventurer() {
        super(new Rectangle(0, 0, 150, 100),
                100, 100, 100, 5,
                new Rectangle(32, 13, 21, 22),
                PlayerID.PLAYER_A, Collections.emptyList());
    }

    public Adventurer(int x, int y, int HP, int AP, int AD, int offset, PlayerID id, Sprite... obstacles) {
        super(new Rectangle(new Point(x, y), new Dimension(100, 100)),
                HP, AP, AD, offset, id, Arrays.asList(obstacles));
    }

    @Override
    protected void onSetupFSM(FSM<State> fsm) {
        fsm.setInitialState(MOVING);

        Gallery runGallery = new SequenceGallery("adventurer/run", new Range(0, 6));
        Gallery attackGallery = new SequenceGallery("adventurer/attack2", new Range(0, 6));
        Gallery dieGallery = new SequenceGallery("adventurer/die", new Range(0, 7));

        fsm.put(MOVING,
                outerState(10, this, runGallery.getImages(), moveAct()));

        fsm.put(ATTACK,
                outerState(10,
                        innerState(this, attackGallery.getImageByPic(0), defaultAct()),
                        innerState(this, attackGallery.getImageByPic(1), defaultAct()),
                        innerState(this, attackGallery.getImageByPic(2), defaultAct()),
                        innerState(this, attackGallery.getImageByPic(3), attackAct()),
                        innerState(this, attackGallery.getImageByPic(4), defaultAct()),
                        innerState(this, attackGallery.getImageByPic(5), defaultAct())));

        fsm.put(DIE,
                outerState(10, this, dieGallery.getImages()));
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        fsm.update();
        Image image = fsm.getImage();
        g.drawImage(image, body.x, body.y, body.width, body.height, null);
    }

    @Override
    public void attack() {
        System.out.println("attack");
        arena.attack(this);
        //attack range point(32, 13) range(21, 12)

    }

    @Override
    public void onClick(Point location) {

    }
}
