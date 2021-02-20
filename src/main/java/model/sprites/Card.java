package model.sprites;

import fsm.FSM;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */

// TODO: click card ?
public class Card extends Sprite {

    private Sprite sprite;
    private Class<Sprite> sClass;

    public static Card createCard(int x, int y, String path, Class<Sprite> spriteClass) {
        return new Card(new Point(x, y), spriteClass);
    }

    public Card(Point location, Class spriteClass) {
        super(new Rectangle(location, new Dimension(100, 100)));
        this.sClass = spriteClass;
    }

    public void summonUnit(Point point) {
        try {
            Sprite newSprite = (Sprite) sClass
                    .getConstructors()[0]
                    .newInstance();
            newSprite.setPoint(point);
            this.arena.addSprite(newSprite);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
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
    protected void onSetupFSM(FSM<State> fsm) {

    }
}
