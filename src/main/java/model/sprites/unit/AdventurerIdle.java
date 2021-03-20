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

import static fsm.OuterState.outerState;
import static model.sprites.State.IDLE;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class AdventurerIdle extends WalkingUnit {


    public AdventurerIdle() {
        super(new Rectangle(0, 0, 100, 100),
                100, 100, 100, 5,
                PlayerID.PLAYER_A, Collections.emptyList());
    }

    public AdventurerIdle(int x, int y, int HP, int AP, int AD, int offset, PlayerID id, Sprite... obstacles) {
        super(new Rectangle(new Point(x, y), new Dimension(100, 100)),
                HP, AP, AD, offset, id, Arrays.asList(obstacles));
    }

    @Override
    protected void onSetupFSM(FSM<State> fsm) {
        Gallery idleGallery = new SequenceGallery("adventurer/idle2", new Range(0, 4));

        fsm.setInitialState(IDLE);
        fsm.put(IDLE,
                outerState(10, this, idleGallery.getImages()));
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
