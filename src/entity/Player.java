package entity;

import main.KeyHandler;
import main.GamePanel;
import main.Constants;

import Bombs.Bombs;
import main.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public int screenX;
    public int screenY;

    public int maxBombs;
    public int totalBombs;
    public Bombs[] arrBombs;

    public int fire = 1;
    public int hitPoint;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle (12, 15, 24, 25);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = 1 * gp.tileSize;
        worldY = 1 * gp.tileSize;
        speed = 3;
        direction = "down";
        maxBombs = 1;
        hitPoint = 1;

        totalBombs = 0;

        arrBombs = new Bombs[20];

        for (int i = 0; i < 20; ++i) {
            arrBombs[i] = new Bombs(this.gp);
        }
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/player_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/player_up_2.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/player_up.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/player_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/player_down_2.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/player_down.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/player_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/player_left_2.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/player_left.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/player_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/player_right_2.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/player_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {

        goUp = false;
        goDown = false;
        goRight = false;
        goLeft = false;

        if (keyH.leftPressed == true || keyH.rightPressed == true || keyH.upPressed == true || keyH.downPressed == true) {
            if (keyH.leftPressed == true) {
                direction = "left";
                goLeft = true;
            }
            if (keyH.rightPressed == true) {
                direction = "right";
                goRight = true;
            }
            if (keyH.downPressed == true) {
                direction = "down";
                goDown = true;
            }
            if (keyH.upPressed == true) {
                direction = "up";
                goUp = true;
            }

            //Check tile collision
            collisionOn = false;
            collisionUp = false;
            collisionDown = false;
            collisionLeft = false;
            collisionRight = false;

            //Kiem tra va cham
            gp.cCheck.checkTile(this);

            //Di chuyen neu khong va cham
            if (goUp == true && collisionUp == false) {
                worldY -= speed;
            }
            if (goDown == true && collisionDown == false) {
                worldY += speed;
            }
            if (goLeft == true && collisionLeft == false) {
                worldX -= speed;
            }
            if (goRight == true && collisionRight == false) {
                worldX += speed;
            }

            spriteCounter++;
            if (spriteCounter > 8) {

                if (spriteNum == 3) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                else if (spriteNum == 1) {
                    spriteNum = 3;
                }
                spriteCounter = 0;
            }

        }
        /*else {
            spriteNum = 3;
        }*/

        if (keyH.spaceTyped== true) {
            if (totalBombs < maxBombs) {
                Sound.play("dropbomb");
                dropBomb();
            }
        }

    }

    public void dropBomb() {
        int x = (worldX + solidArea.x + solidArea.width / 2) / gp.tileSize * gp.tileSize;
        int y = (worldY + solidArea.y + solidArea.height / 2) / gp.tileSize * gp.tileSize;
        int _x = x / gp.tileSize;
        int _y = y / gp.tileSize;

        if (gp.tileM.mapBombs[_x][_y] != 0) {
            return;
        }

        for (int i = 0; i < maxBombs; ++i) {
            if (arrBombs[i].time <= 0) {

                //System.out.println(_x + " " + _y);
                gp.tileM.mapBombs[_x][_y] = i + Constants.bombsCode;
                gp.tileM.mapEConllision[_x][_y] ++;

                arrBombs[i].worldX = x;
                arrBombs[i].worldY = y;
                arrBombs[i].time = Constants.timeBomb / gp.fps * 2;
                arrBombs[i].iP = 1;
                arrBombs[i].ciP = 1;

                totalBombs ++;

                break;
            }
        }

    }

    public void draw(Graphics2D g2) {

        //Ve bomberman
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                else if (spriteNum == 2) {
                    image = up2;
                }
                else if (spriteNum == 3) {
                    image = up;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                else if (spriteNum == 2) {
                    image = down2;
                }
                else if (spriteNum == 3) {
                    image = down;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                else if (spriteNum == 2) {
                    image = left2;
                }
                else if (spriteNum == 3) {
                    image = left;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                else if (spriteNum == 2) {
                    image = right2;
                }
                else if (spriteNum == 3) {
                    image = right;
                }
                break;
        }

        /**
         * kiem tra vi tri de dat bom.
         */
        /*int tileX = (worldX + solidArea.x + solidArea.width / 2) / gp.tileSize;
        int tileY = (worldY + solidArea.y + solidArea.height / 2) / gp.tileSize;

        int renderX = tileX * gp.tileSize - worldX + screenX;
        int renderY = tileY * gp.tileSize - worldY + screenY;
        g2.setColor(Color.yellow);
        g2.fillRect(renderX,renderY,gp.tileSize,gp.tileSize);*/

        //draw bomberman
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        //check hitbox
        /*g2.setColor(Color.red);
        g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);*/
    }

}
