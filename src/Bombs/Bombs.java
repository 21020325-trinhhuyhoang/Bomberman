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
    public int Conllision;

    GamePanel gp;

    public int time, iP, ciP;
    //ciP la do tang giam anh.

    public Bombs(GamePanel gp) {
        int time = 0;
        iP = 1;
        ciP = 1;
        Conllision = 0;
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
         * update bomberman khong the di qua bom.
         */

        int tmp_x = worldX / gp.tileSize;
        int tmp_y = worldY / gp.tileSize;

        if (gp.tileM.mapConllision[tmp_x][tmp_y] <= 0) {
            //player
            int x = gp.player.solidArea.x + gp.player.worldX;
            int y = gp.player.solidArea.y + gp.player.worldY;
            int width = gp.player.solidArea.width;
            int height = gp.player.solidArea.height;

            //bomb
            int _x = this.worldX;
            int _y = this.worldY;

            if ((x + width < _x) || (x > _x + gp.tileSize) || (y + height < _y) || (y > _y + gp.tileSize)) {
                gp.tileM.mapConllision[tmp_x][tmp_y] ++;
                this.Conllision = 1;
            }
        }

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

        /**
         * lam cho sau khi bom no co the di qua.
         */
        if (this.Conllision > 0) {
            gp.tileM.mapConllision[_x][_y] --;
        }
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
