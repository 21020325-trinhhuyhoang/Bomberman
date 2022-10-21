package Enemy;

import Convert.PositionScreen;
import main.Constants;
import main.GamePanel;
import Enemy.SuperEnemy;

import java.awt.*;
import java.util.Random;

/*
 FakeBrick
 Tốc độ: 4
 Hành vi: Đứng im khi player ở xa nhưng khi player lại gần sẽ lao nhanh đến.
 */

public class FBrick extends Enemy {
    public FBrick(int worldX, int worldY, GamePanel gp) {
        super(worldX, worldY, gp);
        this.speed = 4;
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp, SuperEnemy se) {

        //change animation for fbrick
        this.time++;
        if (this.time == Constants.timeChangeAnimationEnemy) {
            this.time = 0;
            this.imgNum++;
            if (this.imgNum > 1) {
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

            drawFBrick(screenX, screenY, g2, gp, se);
        }
    }

    /**
     * render fbrick.
     */
    public void drawFBrick(int x, int y, Graphics2D g2, GamePanel gp, SuperEnemy se) {

        int tileX = (gp.player.worldX + gp.player.solidArea.x + gp.player.solidArea.width / 2) / gp.tileSize;
        int tileY = (gp.player.worldY + gp.player.solidArea.y + gp.player.solidArea.height / 2) / gp.tileSize;

        int X = (this.worldX + gp.tileSize / 2) / gp.tileSize;
        int Y = (this.worldY + gp.tileSize / 2) / gp.tileSize;

        int findPath = AStar.FindPath(gp, X, Y, tileX, tileY);

        if ((Math.abs(X - tileX) > Constants.radiusFBrick && Math.abs(Y - tileY) > Constants.radiusFBrick) || findPath == -1) {
            g2.drawImage(se.image[7][5], x, y, gp.tileSize, gp.tileSize, null);
            return;
        }

        if (this.LR == "left") {
            g2.drawImage(se.image[7][1 + this.imgNum], x, y, gp.tileSize, gp.tileSize, null);
        } else {
            g2.drawImage(se.image[7][3 + this.imgNum], x, y, gp.tileSize, gp.tileSize, null);
        }
    }

    /**
     * move.
     */
    public void move() {

        checkCollision();
        boolean Up = this.collisionUp;
        boolean Down = this.collisionDown;
        boolean Left = this.collisionLeft;
        boolean Right = this.collisionRight;

        int tileX = (gp.player.worldX + gp.player.solidArea.x + gp.player.solidArea.width / 2) / gp.tileSize;
        int tileY = (gp.player.worldY + gp.player.solidArea.y + gp.player.solidArea.height / 2) / gp.tileSize;

        int X = (this.worldX + gp.tileSize / 2) / gp.tileSize;
        int Y = (this.worldY + gp.tileSize / 2) / gp.tileSize;

        if (Math.abs(X - tileX) > Constants.radiusFBrick && Math.abs(Y - tileY) > Constants.radiusFBrick) return;

        int findPath = AStar.FindPath(gp, X, Y, tileX, tileY);

        if (findPath != -1) {
            int x_sc = this.worldX;
            int y_sc = this.worldY;
            int x_sc2 = X * gp.tileSize;
            int y_sc2 = Y * gp.tileSize;
            int direct2 = 0;

            if (y_sc > y_sc2) {
                direct2 = 1;
            }
            if (y_sc < y_sc2) {
                direct2 = 3;
            }
            if (x_sc > x_sc2) {
                direct2 = 2;
            }
            if (x_sc < x_sc2) {
                direct2 = 4;
            }

            this.direction = findPath;

            //System.out.println(this.direction);

            if (Up == true && findPath == 1) {
                this.direction = direct2;
            }
            if (Down == true && findPath == 3) {
                this.direction = direct2;
            }
            if (Left == true && findPath == 2) {
                this.direction = direct2;
            }
            if (Right == true && findPath == 4) {
                this.direction = direct2;
            }
        } else {
            return;
        }

        //Up
        if (this.direction == 1 && Up == false) {
            this.worldY -= this.speed;
        }

        //Down
        if (this.direction == 3 && Down == false) {
            this.worldY += this.speed;
        }

        //Left
        if (this.direction == 2 && Left == false) {
            this.worldX -= this.speed;
            this.LR = "left";
        }

        //Right
        if (this.direction == 4 && Right == false) {
            this.worldX += this.speed;
            this.LR = "right";
        }
    }

}

