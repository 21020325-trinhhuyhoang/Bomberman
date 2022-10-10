package Convert;

import main.GamePanel;

public class PositionScreen {
    public int _x, _y;

    /**
     * lay toa do cong them de render anh len screen.
     */
    public static PositionScreen takePos(GamePanel gp) {
        PositionScreen res = new PositionScreen();
        res._x = -gp.player.worldX + gp.player.screenX;
        res._y = -gp.player.worldY + gp.player.screenY;

        return res;
    }
}
