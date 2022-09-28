package Enemy;

import Convert.PositionScreen;
import main.GamePanel;
import main.Constants;

import java.awt.*;

public class EDeadth {
    public int worldX, worldY;
    public int time;
    public int imgNum;
    public GamePanel gp;
    public int type;

    public EDeadth(int worldX, int worldY, int type, GamePanel gp) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.time = Constants.timeEDeadth;
        this.imgNum = -1;
        this.gp = gp;
    }

    public void draw(Graphics2D g2, SuperEnemy se, SuperEDeadth sed) {
        this.time --;

        if (this.time <= Constants.timeEDeadthUp && this.time % Constants.timeEDeadthMod == 0) {
            this.imgNum ++;
        }

        PositionScreen tmp = PositionScreen.takePos(gp);
        int screenX = worldX + tmp._x;
        int screenY = worldY + tmp._y;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            if (this.imgNum == -1) {
                if (this.type == 0) {
                    g2.drawImage(se.image[0][0], screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
                return;
            }

            g2.drawImage(sed.image[this.imgNum], screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }


}