package model.sprites.unit;

import fsm.FSM;
import model.players.PlayerID;
import model.sprites.Sprite;
import model.sprites.State;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

import static fsm.InnerState.innerState;
import static fsm.OuterState.outerState;
import static fsm.action.Attack.attackAct;
import static fsm.action.Move.moveAct;
import static model.sprites.State.*;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Adventurer extends WalkingUnit {


    public Adventurer() {
        super(new Rectangle(0, 0, 100, 100),
                100, 100, 100, 5,
                PlayerID.PLAYER_A, Collections.emptyList());
    }

    public Adventurer(int x, int y, int HP, int AP, int AD, int offset, PlayerID id, Sprite... obstacles) {
        super(new Rectangle(new Point(x, y), new Dimension(100, 100)),
                HP, AP, AD, offset, id, Arrays.asList(obstacles));
    }

    @Override
    protected void onSetupFSM(FSM<State> fsm) {
        fsm.setInitialState(ATTACK);

        // TODO use Sequence/Gallery instead of a lot of image paths
        fsm.put(MOVING,
                outerState(10,
                        innerState(this, "adventurer/run/0.png", moveAct()),
                        innerState(this, "adventurer/run/1.png", moveAct()),
                        innerState(this, "adventurer/run/2.png", moveAct()),
                        innerState(this, "adventurer/run/3.png", moveAct()),
                        innerState(this, "adventurer/run/4.png", moveAct()),
                        innerState(this, "adventurer/run/5.png", moveAct())));

        fsm.put(ATTACK,
                outerState(10,
                        innerState(this, "adventurer/attack2/adventurer-attack2-00.png", attackAct()),
                        innerState(this, "adventurer/attack2/adventurer-attack2-01.png", attackAct()),
                        innerState(this, "adventurer/attack2/adventurer-attack2-02.png", attackAct()),
                        innerState(this, "adventurer/attack2/adventurer-attack2-03.png", attackAct()),
                        innerState(this, "adventurer/attack2/adventurer-attack2-04.png", attackAct()),
                        innerState(this, "adventurer/attack2/adventurer-attack2-05.png", attackAct())));

        fsm.put(DIE,
                outerState(10,
                        innerState(this, "adventurer/die/adventurer-die-00.png", attackAct()),
                        innerState(this, "adventurer/die/adventurer-die-01.png", attackAct()),
                        innerState(this, "adventurer/die/adventurer-die-02.png", attackAct()),
                        innerState(this, "adventurer/die/adventurer-die-03.png", attackAct()),
                        innerState(this, "adventurer/die/adventurer-die-04.png", attackAct()),
                        innerState(this, "adventurer/die/adventurer-die-05.png", attackAct()),
                        innerState(this, "adventurer/die/adventurer-die-06.png", attackAct())));

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

    }

    @Override
    public void onClick(Point location) {

    }
}
