package Enemy;

import Convert.PositionScreen;
import main.Constants;
import main.GamePanel;
import Enemy.SuperEnemy;

import java.awt.*;

public class Balloom extends Enemy {
    public Balloom(int worldX, int worldY) {
        super(worldX, worldY);
        this.speed = 2;
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp, SuperEnemy se) {

        //change animation for balloom
        this.time++;
        if (this.time == Constants.timeChangeAnimationEnemy) {
            this.time = 0;
            this.imgNum ++;
            if (this.imgNum > 2) {
                this.imgNum = 0;
            }
        }

        PositionScreen tmp = PositionScreen.takePos(gp);
        int screenX = worldX + tmp._x;
        int screenY = worldY + tmp._y;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            drawBallom(screenX, screenY, g2, gp, se);
        }
    }

    /**
     * render balloom.
     */
    public void drawBallom(int x, int y, Graphics2D g2, GamePanel gp, SuperEnemy se) {
         if (this.LR == "left") {
             g2.drawImage(se.image[0][1 + this.imgNum], x, y, gp.tileSize, gp.tileSize, null);
         } else {
             g2.drawImage(se.image[0][4 + this.imgNum], x, y, gp.tileSize, gp.tileSize, null);
         }
    }

}

