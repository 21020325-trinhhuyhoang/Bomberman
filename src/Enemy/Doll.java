package Enemy;

import Convert.PositionScreen;
import main.Constants;
import main.GamePanel;

import java.awt.*;
import java.util.Random;

/*
 Doll
 Tốc độ: 3
 Hành vi: DI chuyển theo 1 đường cố định, chỉ đổi hướng khi gặp vật cản.
 */

public class Doll extends Enemy {
    public Doll(int worldX, int worldY, GamePanel gp) {
        super(worldX, worldY, gp);
        this.speed = 3;
        this.score = 350;
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp, SuperEnemy se) {

        //change animation for doll
        this.time++;
        if (this.time == Constants.timeChangeAnimationEnemy) {
            this.time = 0;
            this.imgNum++;
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

            drawDoll(screenX, screenY, g2, gp, se);
        }
    }

    /**
     * render doll.
     */
    public void drawDoll(int x, int y, Graphics2D g2, GamePanel gp, SuperEnemy se) {
        if (this.LR == "left") {
            g2.drawImage(se.image[6][1 + this.imgNum], x, y, gp.tileSize, gp.tileSize, null);
        } else {
            g2.drawImage(se.image[6][4 + this.imgNum], x, y, gp.tileSize, gp.tileSize, null);
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

        int x = this.worldX;
        int y = this.worldY;
        Random rand;
        int a = -1;
        boolean changeDirect = false;
        int newDirect;

        if (this.direction == 1 && Up == true) {
            changeDirect = true;
        }

        if (this.direction == 3 && Down == true) {
            changeDirect = true;
        }

        if (this.direction == 2 && Left == true) {
            changeDirect = true;
        }

        if (this.direction == 4 && Right == true) {
            changeDirect = true;
        }

        if (changeDirect == true) {
            newDirect = -1;
            rand = new Random();
            a = rand.nextInt(1000000);

            a = a % 4 + 1;

            if (a == 1 && Up == false) {
                newDirect = a;
            } else if (a == 2 && Left == false) {
                newDirect = a;
            } else if (a == 3 && Down == false) {
                newDirect = a;
            } else if (a == 4 && Right == false) {
                newDirect = a;
            } else {

                if (Up == false) {
                    rand = new Random();
                    a = rand.nextInt(3);
                    if (a == 1 || newDirect == -1) {
                        newDirect = 1;
                    }
                }

                if (Down == false) {
                    rand = new Random();
                    a = rand.nextInt(3);
                    if (a == 1 || newDirect == -1) {
                        newDirect = 3;
                    }
                }

                if (Left == false) {
                    rand = new Random();
                    a = rand.nextInt(3);
                    if (a == 1 || newDirect == -1) {
                        newDirect = 2;
                    }
                }

                if (Right == false) {
                    rand = new Random();
                    a = rand.nextInt(3);
                    if (a == 1 || newDirect == -1) {
                        newDirect = 4;

                    }
                }

                if (newDirect != -1) {
                    this.direction = newDirect;
                }
            }
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

