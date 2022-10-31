package Bombs;

import Convert.PositionScreen;
import main.Constants;
import main.GamePanel;

import java.awt.*;

public class ExToxic {
    public int time;
    public int typeImage;
    public int type;
    public int worldX, worldY;
    public int cType;

    public ExToxic(int worldX, int worldY, int time, int type) {
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
    public void draw(Graphics2D g2, GamePanel gp, SuperToxic st) {
        if (this.time % Constants.timeExplosionMod == 0) {
            if (this.typeImage == 2 && this.cType == 1) this.cType = -1;
            this.typeImage += this.cType;
        }

        PositionScreen tmp = PositionScreen.takePos(gp);
        int screenX = worldX + tmp._x;
        int screenY = worldY + tmp._y;

        if (typeImage < 0) return;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(st.image[typeImage][type], screenX, screenY, gp.tileSize, gp.tileSize, null);
        }

    }


}
