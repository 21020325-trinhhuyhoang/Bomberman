package Enemy;

import Convert.PositionScreen;
import main.Constants;
import main.GamePanel;

import java.awt.*;
import java.util.Random;

/*
 Potan:
 Tốc độ: 2
 Hành vi: Di chuyển ngẫu nhiên qua các khối gạch và thỉnh thoảng sẽ đuổi theo người chơi. Là kẻ thù nguy hiểm
          nhất do có thể đi xuyên tường với tốc độ cao.
 */

public class Pontan extends Enemy {

    public int timeChange;
    public int color;
    public int status; //Trang thai = 1 la di chuyen tu do = 2 la duoi theo bomberman.

    public Pontan(int worldX, int worldY, GamePanel gp) {
        super(worldX, worldY, gp);
        this.speed = 2;
        this.timeChange = 1000;
        Random rand = new Random();
        this.color = rand.nextInt(1000) % 2 + 1;
        this.score = 400;
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp, SuperEnemy se) {

        //change animation for potan
        this.time++;
        if (this.time == Constants.timePontanChange) {
            this.time = 0;
            this.imgNum++;
            if (this.imgNum > 3) {
                this.imgNum = 0;
                //change color
                this.color = 3 - this.color;
            }
        }

        PositionScreen tmp = PositionScreen.takePos(gp);
        int screenX = worldX + tmp._x;
        int screenY = worldY + tmp._y;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            drawPontan(screenX, screenY, g2, gp, se);
        }
    }

    /**
     * render Pontan.
     */
    public void drawPontan(int x, int y, Graphics2D g2, GamePanel gp, SuperEnemy se) {
        if (this.color == 1) {
            g2.drawImage(se.image[3][1 + this.imgNum], x, y, gp.tileSize, gp.tileSize, null);
        } else {
            g2.drawImage(se.image[3][6 + this.imgNum], x, y, gp.tileSize, gp.tileSize, null);
        }
    }

    /**
     * move.
     */
    public void move() {

        checkCollisionWall();
        boolean Up = this.collisionWallUp;
        boolean Down = this.collisionWallDown;
        boolean Left = this.collisionWallLeft;
        boolean Right = this.collisionWallRight;

        int tileX = (gp.player.worldX + gp.player.solidArea.x + gp.player.solidArea.width / 2) / gp.tileSize;
        int tileY = (gp.player.worldY + gp.player.solidArea.y + gp.player.solidArea.height / 2) / gp.tileSize;

        int X = (this.worldX + gp.tileSize / 2) / gp.tileSize;
        int Y = (this.worldY + gp.tileSize / 2) / gp.tileSize;

        this.timeChange--;
        if (this.timeChange <= 0) {
            Random rand = new Random();
            this.status = rand.nextInt(10000) % 2 + 1;
            rand = new Random();
            this.timeChange = rand.nextInt(10000) % 1000 + 1000;
            if (this.status == 2) { this.timeChange = this.timeChange / 2; }
        }

        int findPath2 = -1;
        if (this.status == 2) {
            findPath2 = AStar.FindPath2(gp, X, Y, tileX, tileY);
        }

        if (findPath2 != -1) {
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

            this.direction = findPath2;

            if (Up == true && findPath2 == 1) {
                this.direction = direct2;
            }
            if (Down == true && findPath2 == 3) {
                this.direction = direct2;
            }
            if (Left == true && findPath2 == 2) {
                this.direction = direct2;
            }
            if (Right == true && findPath2 == 4) {
                this.direction = direct2;
            }
        } else {
            int x = this.worldX;
            int y = this.worldY;
            Random rand;
            int a = -1;
            boolean changeDirect = false;
            int newDirect;

            if (x % gp.tileSize == 0 && y % gp.tileSize == 0) {
                int _x = x / gp.tileSize;
                int _y = y / gp.tileSize;
                //System.out.println("_____" + x + " " + y + "| " + _x + " " + _y);

                if (_x % 2 == 1 && _y % 2 == 1) {
                    rand = new Random();
                    a = rand.nextInt(1000000);
                    if (a % 4 == 2) {
                        changeDirect = true;
                    }
                }
            }

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
                if (a % 2 == 0) {

                    if (this.direction == 1 || this.direction == 3) {
                        if (a % 2 == 0 && Left == false) {
                            this.direction = 2;
                        } else if (a % 2 == 1 && Right == false) {
                            this.direction = 4;
                        }
                    }
                } else {

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
                    }

                    if (newDirect != -1) {
                        this.direction = newDirect;
                    }
                }
            }

            //doi hunog nguoc lai
            rand = new Random();
            a = rand.nextInt(10000);

            if (a % 1000 == 0) {
                if (this.direction == 1 && Down == false) {
                    this.direction = 3;
                } else if (this.direction == 3 && Up == false) {
                    this.direction = 1;
                } else if (this.direction == 2 && Right == false) {
                    this.direction = 4;
                } else if (this.direction == 4 && Left == false) {
                    this.direction = 2;
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
