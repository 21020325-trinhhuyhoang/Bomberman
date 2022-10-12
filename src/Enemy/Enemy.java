package Enemy;

import entity.Player;
import main.Constants;
import main.GamePanel;

import java.awt.*;

public abstract class Enemy {
    public int worldX, worldY;
    public int speed;
    public int direction;
    public String LR;

    //thu tu cua image duoc render
    public int time, imgNum;
    public int hitPoint;
    public GamePanel gp;

    public boolean collisionUp, collisionDown, collisionLeft, collisionRight;
    public boolean collisionWallUp, collisionWallDown, collisionWallLeft, collisionWallRight;

    public Enemy(int worldX, int worldY, GamePanel gp) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = 2;
        this.LR = "left";
        this.time = 0;
        this.imgNum = 0;
        this.hitPoint = 1;
        this.gp = gp;
        gp.TotalEnemy ++;
    }

    public abstract void draw(Graphics2D g2, GamePanel gp, SuperEnemy se);

    public abstract void move();

    /**
     * check deadth.
     */
    public boolean checkDeadth() {

        int x = worldX;
        int y = worldY;

        //trai tren
        int _x = (x + Constants.hitBoxE) / gp.tileSize;
        int _y = (y + Constants.hitBoxE) / gp.tileSize;

        if (gp.tileM.mapExplosion[_x][_y] > 0) {
            return true;
        }

        //phai tren
        _x = (x + gp.tileSize - 1 - Constants.hitBoxE) / gp.tileSize;
        _y = (y + Constants.hitBoxE) / gp.tileSize;

        if (gp.tileM.mapExplosion[_x][_y] > 0) {
            return true;
        }

        //trai duoi
        _x = (x + Constants.hitBoxE) / gp.tileSize;
        _y = (y + gp.tileSize - 1 - Constants.hitBoxE) / gp.tileSize;

        if (gp.tileM.mapExplosion[_x][_y] > 0) {
            return true;
        }

        //phai duoi
        _x = (x + gp.tileSize - 1 - Constants.hitBoxE) / gp.tileSize;
        _y = (y + gp.tileSize - 1 - Constants.hitBoxE) / gp.tileSize;

        if (gp.tileM.mapExplosion[_x][_y] > 0) {
            return true;
        }

        return false;
    }

    /**
     * check collision.
     */
    public void checkCollision() {
        int x = worldX;
        int y = worldY;
        int _x1, _x2;
        int _y1, _y2;

        this.collisionUp = false;
        this.collisionDown = false;
        this.collisionLeft = false;
        this.collisionRight = false;

        //Up
        _x1 = (x) / gp.tileSize;
        _y1 = (y - this.speed) / gp.tileSize;

        _x2 = (x + gp.tileSize - 1) / gp.tileSize;
        _y2 = (y - this.speed) / gp.tileSize;
        if (gp.tileM.mapEConllision[_x1][_y1] > 0 || gp.tileM.mapEConllision[_x2][_y2] > 0) {
            this.collisionUp = true;
        }

        //Down
        _x1 = (x) / gp.tileSize;
        _y1 = (y + gp.tileSize - 1 + this.speed) / gp.tileSize;

        _x2 = (x + gp.tileSize - 1) / gp.tileSize;
        _y2 = (y + gp.tileSize - 1 + this.speed) / gp.tileSize;
        if (gp.tileM.mapEConllision[_x1][_y1] > 0 || gp.tileM.mapEConllision[_x2][_y2] > 0) {
            this.collisionDown = true;
        }

        //Left
        _x1 = (x - this.speed) / gp.tileSize;
        _y1 = (y) / gp.tileSize;

        _x2 = (x - this.speed) / gp.tileSize;
        _y2 = (y + gp.tileSize - 1) / gp.tileSize;
        if (gp.tileM.mapEConllision[_x1][_y1] > 0 || gp.tileM.mapEConllision[_x2][_y2] > 0) {
            this.collisionLeft = true;
        }

        //Right
        _x1 = (x + gp.tileSize - 1 + this.speed) / gp.tileSize;
        _y1 = (y) / gp.tileSize;

        _x2 = (x + gp.tileSize - 1 + this.speed) / gp.tileSize;
        _y2 = (y + gp.tileSize - 1) / gp.tileSize;
        if (gp.tileM.mapEConllision[_x1][_y1] > 0 || gp.tileM.mapEConllision[_x2][_y2] > 0) {
            this.collisionRight = true;
        }

    }

    /**
     * check collision wall.
     */
    public void checkCollisionWall() {
        int x = worldX;
        int y = worldY;
        int _x1, _x2;
        int _y1, _y2;

        this.collisionWallUp = false;
        this.collisionWallDown = false;
        this.collisionWallLeft = false;
        this.collisionWallRight = false;

        //Up
        _x1 = (x) / gp.tileSize;
        _y1 = (y - this.speed) / gp.tileSize;

        _x2 = (x + gp.tileSize - 1) / gp.tileSize;
        _y2 = (y - this.speed) / gp.tileSize;
        if (gp.tileM.mapTileNum[_x1][_y1] == 1 || gp.tileM.mapTileNum[_x2][_y2] == 1 || gp.tileM.mapBombs[_x1][_y1] > 0 || gp.tileM.mapBombs[_x2][_y2] > 0) {
            this.collisionWallUp = true;
        }

        //Down
        _x1 = (x) / gp.tileSize;
        _y1 = (y + gp.tileSize - 1 + this.speed) / gp.tileSize;

        _x2 = (x + gp.tileSize - 1) / gp.tileSize;
        _y2 = (y + gp.tileSize - 1 + this.speed) / gp.tileSize;
        if (gp.tileM.mapTileNum[_x1][_y1] == 1 || gp.tileM.mapTileNum[_x2][_y2] == 1 || gp.tileM.mapBombs[_x1][_y1] > 0 || gp.tileM.mapBombs[_x2][_y2] > 0) {
            this.collisionWallDown = true;
        }

        //Left
        _x1 = (x - this.speed) / gp.tileSize;
        _y1 = (y) / gp.tileSize;

        _x2 = (x - this.speed) / gp.tileSize;
        _y2 = (y + gp.tileSize - 1) / gp.tileSize;
        if (gp.tileM.mapTileNum[_x1][_y1] == 1 || gp.tileM.mapTileNum[_x2][_y2] == 1 || gp.tileM.mapBombs[_x1][_y1] > 0 || gp.tileM.mapBombs[_x2][_y2] > 0) {
            this.collisionWallLeft = true;
        }

        //Right
        _x1 = (x + gp.tileSize - 1 + this.speed) / gp.tileSize;
        _y1 = (y) / gp.tileSize;

        _x2 = (x + gp.tileSize - 1 + this.speed) / gp.tileSize;
        _y2 = (y + gp.tileSize - 1) / gp.tileSize;
        if (gp.tileM.mapTileNum[_x1][_y1] == 1 || gp.tileM.mapTileNum[_x2][_y2] == 1 || gp.tileM.mapBombs[_x1][_y1] > 0 || gp.tileM.mapBombs[_x2][_y2] > 0) {
            this.collisionWallRight = true;
        }

    }

    public boolean checkColPlayer() {
        Player player = gp.player;
        int x1 = worldX + Constants.hitBoxE;
        int y1 = worldY + Constants.hitBoxE;
        int x2 = worldX + Constants.hitBoxE + Constants.hitBoxERadius - 1;
        int y2 = y1;
        int x3 = x1;
        int y3 = worldY + Constants.hitBoxE + Constants.hitBoxERadius - 1;
        int x4 = x2;
        int y4 = y3;

        int _x1 = player.worldX + player.solidArea.x;
        int _y1 = player.worldY + player.solidArea.y;
        int _x2 = player.worldX + player.solidArea.x + player.solidArea.width - 1;
        int _y2 = _y1;
        int _x3 = _x1;
        int _y3 = player.worldY + player.solidArea.y + player.solidArea.height - 1;
        int _x4 = _x2;
        int _y4 = _y3;

        //E: trai tren + P: phai duoi
        if (_x4 >= x1 && _x4 <= x2 && _y4 >= y1 && _y4 <= y3) {
            return true;
        }

        //E: phai tren + P: trai duoi
        if (_x3 >= x1 && _x3 <= x2 && _y3 >= y2 && _y3 <= y4) {
            return true;
        }

        //E: trai duoi + P: phai tren
        if (_x2 >= x1 && _x2 <= x2 && _y2 >= y1 && _y2 <= y3) {
            return true;
        }

        //E: phai duoi + P: trai tren
        if (_x1 >= x1 && _x1 <= x2 && _y1 >= y1 && _y1 <= y3) {
            return true;
        }




        return false;
    }
}
