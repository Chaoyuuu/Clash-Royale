package fsm.action;

import model.sprites.Sprite;
import model.sprites.unit.Unit;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Move implements Action {

    public static Move moveAct() {
        return new Move();
    }

    @Override
    public void execute(Sprite sprite) {
        ((Unit) sprite).move();
    }
}
