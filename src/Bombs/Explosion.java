package Bombs;

import Convert.PositionScreen;
import main.Constants;
import main.GamePanel;

import java.awt.*;

public class Explosion {
    public int time;
    public int typeImage;
    public int type;
    public int worldX, worldY;
    public int cType;

    public Explosion(int worldX, int worldY, int time, int type) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.time = time;
        this.type = type;
        this.typeImage = 0;
        this.cType = 1;
    }

    /**
     * ve vu no.
     */
    public void draw(Graphics2D g2, GamePanel gp, SuperExplosion sp) {
        if (this.time % Constants.timeExplosionMod == 0) {
            if (this.typeImage == 2 && this.cType == 1) this.cType = -1;
            this.typeImage += this.cType;
        }

        PositionScreen tmp = PositionScreen.takePos(gp);
        int screenX = worldX + tmp._x;
        int screenY = worldY + tmp._y;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(sp.image[typeImage][type], screenX, screenY, gp.tileSize, gp.tileSize, null);
        }

    }


}
