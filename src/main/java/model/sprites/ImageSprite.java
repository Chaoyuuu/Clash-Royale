package model.sprites;

import fsm.FSM;
import utils.ImageUtils;

import java.awt.*;

import static fsm.InnerState.innerState;
import static fsm.OuterState.outerState;
import static model.sprites.State.STATIC;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class ImageSprite extends Sprite {

    public ImageSprite() {
        super(new Rectangle(0, 0, 50, 50));
    }

    @Override
    public void setPoint(Point point) {
        super.setPoint(point);
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
        fsm.setInitialState(STATIC);
        fsm.put(STATIC,
                outerState(100, innerState(this, "adventurer/adventurer-run-00.png")));
    }
}
