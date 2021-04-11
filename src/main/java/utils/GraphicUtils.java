package utils;

import model.sprites.unit.Unit;

import java.awt.*;

import static model.players.PlayerID.PLAYER_B;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class GraphicUtils {

    //TODO: rename to spriteGraphicUtil
    public static void drawImage(Image image, Unit unit, Graphics g) {
        Rectangle range = unit.getImageRange();
        if (unit.getPlayerID() == PLAYER_B) {
            g.drawImage(image, range.x, range.y, range.width, range.height, null);
        } else {
            g.drawImage(image, range.x + range.width, range.y, -range.width, range.height, null);
        }
    }

    public static void drawBoxesAndHP(Unit unit, Graphics g) {
        drawHP(unit.getHP(), unit.getImageRange(), g);
        drawBox(unit.getAttackRange(), Color.CYAN, g);
        drawBox(unit.getBody(), Color.YELLOW, g);
        drawBox(unit.getImageRange(), Color.RED, g);
    }

    public static void drawBox(Rectangle rect, Color color, Graphics g) {
        g.setColor(color);
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
    }

    public static void drawHP(int HP, Rectangle image, Graphics g) {
        g.setColor(Color.ORANGE);
        g.drawString(String.valueOf(HP), image.x, image.y - 5);
    }
}
