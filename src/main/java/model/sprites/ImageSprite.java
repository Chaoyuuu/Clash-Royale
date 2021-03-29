package model.sprites;

import fsm.FSM;
import galleries.Gallery;
import galleries.Range;
import galleries.SequenceGallery;

import java.awt.*;

import static fsm.OuterState.outerState;
import static fsm.action.Move.moveAct;
import static model.sprites.State.MOVING;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class ImageSprite extends Sprite {

    public ImageSprite() {
        super(new Rectangle(0, 0, 50, 50),
                new Rectangle(0, 0, 50, 50));
    }

    @Override
    public void setLocation(Point point) {
        super.setLocation(point);
    }

    @Override
    public void onClick(Point location) {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        fsm.update();
        g.drawImage(fsm.getImage(), body.x, body.y, body.width, body.height, null);
    }

    @Override
    protected void onSetupFSM(FSM<State> fsm) {
        fsm.setInitialState(MOVING);
        Gallery runGallery = new SequenceGallery("adventurer/run", new Range(0, 6));
        fsm.put(MOVING,
                outerState(10, this, runGallery.getImages(), moveAct()));
    }
}
