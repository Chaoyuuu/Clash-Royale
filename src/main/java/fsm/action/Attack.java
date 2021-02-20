package fsm.action;


import model.sprites.Sprite;
import model.sprites.unit.Unit;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Attack implements Action {

    public static Attack attackAct() {
        return new Attack();
    }

    @Override
    public void execute(Sprite sprite) {
        ((Unit) sprite).attack();
    }
}
