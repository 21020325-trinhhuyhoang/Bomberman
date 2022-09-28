package Enemy;

import main.GamePanel;

import java.awt.*;

public abstract class Enemy {
    public int worldX, worldY;
    public int speed;
    public String direction;
    public String LR;

    //thu tu cua image duoc render
    public int time,imgNum;
    public int hitPoint;

    public Enemy(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = "down";
        this.LR = "left";
        this.time = 0;
        this.imgNum = 0;
        this.hitPoint = 1;
    }

    public abstract void draw(Graphics2D g2, GamePanel gp, SuperEnemy se);

    //public abstract void move(GamePanel gp);

    /**
     * check deadth.
     */
    public boolean checkDeadth(GamePanel gp) {

        int x = worldX;
        int y = worldY;

        //trai tren
        int _x = x / gp.tileSize;
        int _y = y / gp.tileSize;

        if (gp.tileM.mapExplosion[_x][_y] > 0) {
            return true;
        }

        //phai tren
        _x = (x + gp.tileSize - 1) / gp.tileSize;
        _y = y / gp.tileSize;

        if (gp.tileM.mapExplosion[_x][_y] > 0) {
            return true;
        }

        //trai duoi
        _x = x / gp.tileSize;
        _y = (y + gp.tileSize - 1) / gp.tileSize;

        if (gp.tileM.mapExplosion[_x][_y] > 0) {
            return true;
        }

        //phai duoi
        _x = (x + gp.tileSize - 1) / gp.tileSize;
        _y = (y + gp.tileSize - 1) / gp.tileSize;

        if (gp.tileM.mapExplosion[_x][_y] > 0) {
            return true;
        }

        return false;
    }

}
