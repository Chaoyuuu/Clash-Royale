package fsm.action;

import model.sprites.Sprite;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class DefaultAction implements Action {

    public static DefaultAction defaultAct() {
        return new DefaultAction();
    }

    @Override
    public void execute(Sprite sprite) {

    }
}
