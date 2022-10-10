package Enemy;

import Convert.PositionScreen;
import main.Constants;
import main.GamePanel;
import Enemy.SuperEnemy;

import java.awt.*;
import java.util.Random;

/*
 Balloom
 Tốc độ: 1
 Hành vi: DI chuyển ngẫu nhiên
 */

public class Balloom extends Enemy {
    public Balloom(int worldX, int worldY, GamePanel gp) {
        super(worldX, worldY, gp);
        this.speed = 1;
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp, SuperEnemy se) {

        //change animation for balloom
        this.time++;
        if (this.time == Constants.timeChangeAnimationEnemy) {
            this.time = 0;
            this.imgNum ++;
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

            drawBallom(screenX, screenY, g2, gp, se);
        }
    }

    /**
     * render balloom.
     */
    public void drawBallom(int x, int y, Graphics2D g2, GamePanel gp, SuperEnemy se) {
         if (this.LR == "left") {
             g2.drawImage(se.image[0][1 + this.imgNum], x, y, gp.tileSize, gp.tileSize, null);
         } else {
             g2.drawImage(se.image[0][4 + this.imgNum], x, y, gp.tileSize, gp.tileSize, null);
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

