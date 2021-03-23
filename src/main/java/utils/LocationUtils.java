package utils;

import model.players.PlayerID;

import java.awt.*;

import static model.players.PlayerID.PLAYER_B;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class LocationUtils {
    public static void updateLocationByPlayerId(Rectangle rect, Rectangle base, Point offset, PlayerID id) {
        if (id == PLAYER_B) {
            rect.setLocation(offset.x + base.x, offset.y + base.y);
        } else {
            int baseX = base.x + base.width;
            int baseY = base.y;
            rect.setLocation(baseX - offset.x - rect.width, baseY + offset.y);
        }
    }
}
