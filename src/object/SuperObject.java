package object;

import main.GamePanel;
import Convert.PositionScreen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    
    public SuperObject(int worldX,int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        PositionScreen tmp = PositionScreen.takePos(gp);
        int screenX = worldX + tmp._x;
        int screenY = worldY + tmp._y;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    /**
     * check if player take this power_up.
     */
    public boolean check(GamePanel gp) {
        int x,y,_x,_y, tileSize = gp.tileSize;
        x = gp.player.worldX;
        y = gp.player.worldY;
        _x = this.worldX;
        _y = this.worldY;

        if (x >= _x && x <= _x + tileSize - 1 && y >= _y && y <= _y + tileSize - 1) {
            return true;
        }

        if (x + tileSize - 1 >= _x && x + tileSize - 1 <= _x + tileSize - 1 && y >= _y && y <= _y + tileSize - 1) {
            return true;
        }

        if (x >= _x && x <= _x + tileSize - 1 && y + tileSize - 1 >= _y && y + tileSize - 1 <= _y + tileSize - 1) {
            return true;
        }

        if (x + tileSize - 1 >= _x && x + tileSize - 1 <= _x + tileSize - 1 && y + tileSize - 1 >= _y && y + tileSize - 1 <= _y + tileSize - 1) {
            return true;
        }

        return false;
    }

    public boolean checkExplo(GamePanel gp) {
        int _x = this.worldX / gp.tileSize;
        int _y = this.worldY / gp.tileSize;
        if (gp.tileM.mapExplosion[_x][_y] > 0) {
            return true;
        }

        return false;
    }

}
