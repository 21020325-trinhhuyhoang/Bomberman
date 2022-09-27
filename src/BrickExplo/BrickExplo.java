package BrickExplo;

import Convert.PositionScreen;
import main.Constants;
import main.GamePanel;

import java.awt.*;

public class BrickExplo {
    public int worldX, worldY;
    public int time;
    public int type;

    public BrickExplo(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.time = Constants.timeBrickExplo;
        this.type = 0;
    }

    public void draw(Graphics2D g2, GamePanel gp, SuperBrickExplo sl) {
        if (this.time % Constants.timeBrickExploMod == 0) {
            this.type ++;
        }

        PositionScreen tmp = PositionScreen.takePos(gp);
        int screenX = worldX + tmp._x;
        int screenY = worldY + tmp._y;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(sl.image[type], screenX, screenY, gp.tileSize, gp.tileSize, null);
        }

    }
}
