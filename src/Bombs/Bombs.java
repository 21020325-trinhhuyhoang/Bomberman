package Bombs;

import Convert.PositionScreen;
import main.GamePanel;
import entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bombs {
    public BufferedImage image, image1, image2;

    public int worldX, worldY;

    GamePanel gp;

    public int time, iP, ciP;
    //ciP la do tang giam anh.

    public Bombs(GamePanel gp) {
        int time = 0;
        iP = 1;
        ciP = 1;
        this.gp = gp;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/bomb.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/bomb_1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/bomb_2.png"));

        } catch (IOException e) {
            System.out.println("Khong load dc file anh bombs!");
            e.printStackTrace();
        }
    }

    public void update(GamePanel gp,Player player) {
        this.time--;

        /**
         * doi image.
         */
        if (this.time % (300 / gp.fps * 2) == 0) {
            if (ciP == 1 && iP == 3) { ciP = -1; }
            if (ciP == -1 && iP == 1) { ciP = 1; }

            iP += ciP;
        }

        //bom no
        if (this.time <= 0) {
            player.totalBombs --;
            BombExplove();
        }
    }

    public void BombExplove() {

        int _x = this.worldX / gp.tileSize;
        int _y = this.worldY / gp.tileSize;
        gp.tileM.mapBombs[_x][_y] = 0;
    }

    public void draw(Graphics2D g2, GamePanel gp) {

        PositionScreen tmp = PositionScreen.takePos(gp);
        int screenX = worldX + tmp._x;
        int screenY = worldY + tmp._y;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            //g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            drawbomb(iP, screenX, screenY, g2, gp);
        }
    }

    /**
     * draw bomb.
     */
    public void drawbomb(int i, int x, int y, Graphics2D g2, GamePanel gp) {
        if (i == 1) {
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        }
        else if (i == 2) {
            g2.drawImage(image1, x, y, gp.tileSize, gp.tileSize, null);
        }
        else if (i == 3) {
            g2.drawImage(image2, x, y, gp.tileSize, gp.tileSize, null);
        }
    }
}