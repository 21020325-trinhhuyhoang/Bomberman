package Bombs;

import BrickExplo.BrickExplo;
import BrickExplo.SuperBrickExplo;
import Convert.PositionScreen;
import main.GamePanel;
import entity.Player;
import main.Constants;
import main.Sound;
import object.Door;
import object.PowerUp_Bombs;
import object.PowerUp_Flames;
import object.PowerUp_Speed;

import javax.imageio.ImageIO;
import javax.lang.model.type.ArrayType;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

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

    public void update(GamePanel gp, Player player, ArrayList<Explosion> listExplosion) {
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
                gp.tileM.mapConllision[tmp_x][tmp_y]++;
                this.Conllision = 1;
            }
        }

        /**
         * doi image.
         */
        if (this.time % Constants.timeBombMod == 0) {
            if (ciP == 1 && iP == 3) {
                ciP = -1;
            }
            if (ciP == -1 && iP == 1) {
                ciP = 1;
            }

            iP += ciP;
        }

        //bom no
        if (this.time <= 0) {
            player.totalBombs--;
            Sound.play("explosion1");
            BombExplosion(listExplosion, player);
        }
    }

    public void BombExplosion(ArrayList<Explosion> listExplosion, Player player) {

        BrickExplo newBrickExplo;
        int _x = this.worldX / gp.tileSize;
        int _y = this.worldY / gp.tileSize;
        gp.tileM.mapBombs[_x][_y] = 0;

        int fire = player.fire;

        /**
         * lam cho sau khi bom no co the di qua.
         */
        if (this.Conllision > 0) {
            gp.tileM.mapConllision[_x][_y]--;
            gp.tileM.mapEConllision[_x][_y]--;
        }

        //center
        gp.tileM.mapExplosion[_x][_y]++;
        Explosion explosion = new Explosion(this.worldX, this.worldY, Constants.timeExplosion, 0);
        addExplosion(explosion, listExplosion);

        //left
        for (int i = 1; i <= fire; ++i) {

            if (_x - i <= 0) break;
            if (gp.tileM.mapTileNum[_x - i][_y] == 1) {
                break;
            }

            if (gp.tileM.mapTileNum[_x - i][_y] == 2) {
                gp.tileM.mapTileNum[_x - i][_y] = 0;
                gp.tileM.mapConllision[_x - i][_y]--;
                gp.tileM.mapEConllision[_x - i][_y]--;
                createObject(_x - i, _y);

                newBrickExplo = new BrickExplo((_x - i) * gp.tileSize, (_y) * gp.tileSize);
                addBrickExplo(this.gp.listBrickExplo, newBrickExplo);
                break;
            }

            if (gp.tileM.mapBombs[_x - i][_y] != 0) {
                int bomb = gp.tileM.mapBombs[_x - i][_y] - Constants.bombsCode;
                player.arrBombs[bomb].time = 1;
                break;
            }

            if (gp.tileM.mapExplosion[_x - i][_y] > 0) break;
            gp.tileM.mapExplosion[_x - i][_y]++;

            //last left
            if (i == fire) {
                explosion = new Explosion(this.worldX - i * gp.tileSize, this.worldY, Constants.timeExplosion, 2);
                addExplosion(explosion, listExplosion);
                break;
            }

            explosion = new Explosion(this.worldX - i * gp.tileSize, this.worldY, Constants.timeExplosion, 1);
            addExplosion(explosion, listExplosion);
        }

        //right
        for (int i = 1; i <= fire; ++i) {

            if (_x + i >= gp.maxWorldCol) break;
            if (gp.tileM.mapTileNum[_x + i][_y] == 1) {
                break;
            }

            if (gp.tileM.mapTileNum[_x + i][_y] == 2) {
                gp.tileM.mapTileNum[_x + i][_y] = 0;
                gp.tileM.mapConllision[_x + i][_y]--;
                gp.tileM.mapEConllision[_x + i][_y]--;
                createObject(_x + i, _y);

                newBrickExplo = new BrickExplo((_x + i) * gp.tileSize, (_y) * gp.tileSize);
                addBrickExplo(this.gp.listBrickExplo, newBrickExplo);
                break;
            }

            if (gp.tileM.mapBombs[_x + i][_y] != 0) {
                int bomb = gp.tileM.mapBombs[_x + i][_y] - Constants.bombsCode;
                player.arrBombs[bomb].time = 1;
                break;
            }

            if (gp.tileM.mapExplosion[_x + i][_y] > 0) break;
            gp.tileM.mapExplosion[_x + i][_y]++;

            //last right
            if (i == fire) {
                explosion = new Explosion(this.worldX + i * gp.tileSize, this.worldY, Constants.timeExplosion, 3);
                addExplosion(explosion, listExplosion);
                break;
            }

            explosion = new Explosion(this.worldX + i * gp.tileSize, this.worldY, Constants.timeExplosion, 1);
            addExplosion(explosion, listExplosion);
        }

        //down
        for (int i = 1; i <= fire; ++i) {

            if (_y - i <= 0) break;
            if (gp.tileM.mapTileNum[_x][_y - i] == 1) {
                break;
            }

            if (gp.tileM.mapTileNum[_x][_y - i] == 2) {
                gp.tileM.mapTileNum[_x][_y - i] = 0;
                gp.tileM.mapConllision[_x][_y - i]--;
                gp.tileM.mapEConllision[_x][_y - i]--;
                createObject(_x, _y - i);

                newBrickExplo = new BrickExplo((_x) * gp.tileSize, (_y - i) * gp.tileSize);
                addBrickExplo(this.gp.listBrickExplo, newBrickExplo);
                break;
            }

            if (gp.tileM.mapBombs[_x][_y - i] != 0) {
                int bomb = gp.tileM.mapBombs[_x][_y - i] - Constants.bombsCode;
                player.arrBombs[bomb].time = 1;
                break;
            }

            if (gp.tileM.mapExplosion[_x][_y - i] > 0) break;
            gp.tileM.mapExplosion[_x][_y - i]++;

            //last down
            if (i == fire) {
                explosion = new Explosion(this.worldX, this.worldY - i * gp.tileSize, Constants.timeExplosion, 6);
                addExplosion(explosion, listExplosion);
                break;
            }

            explosion = new Explosion(this.worldX, this.worldY - i * gp.tileSize, Constants.timeExplosion, 4);
            addExplosion(explosion, listExplosion);
        }

        //top
        for (int i = 1; i <= fire; ++i) {

            if (_y + i >= gp.maxWorldRow) break;
            if (gp.tileM.mapTileNum[_x][_y + i] == 1) {
                break;
            }

            if (gp.tileM.mapTileNum[_x][_y + i] == 2) {
                gp.tileM.mapTileNum[_x][_y + i] = 0;
                gp.tileM.mapConllision[_x][_y + i]--;
                gp.tileM.mapEConllision[_x][_y + i]--;
                createObject(_x, _y + i);

                newBrickExplo = new BrickExplo((_x) * gp.tileSize, (_y + i) * gp.tileSize);
                addBrickExplo(this.gp.listBrickExplo, newBrickExplo);
                break;
            }

            if (gp.tileM.mapBombs[_x][_y + i] != 0) {
                int bomb = gp.tileM.mapBombs[_x][_y + i] - Constants.bombsCode;
                player.arrBombs[bomb].time = 1;
                break;
            }

            if (gp.tileM.mapExplosion[_x][_y + i] > 0) break;
            gp.tileM.mapExplosion[_x][_y + i]++;

            //last top
            if (i == fire) {
                explosion = new Explosion(this.worldX, this.worldY + i * gp.tileSize, Constants.timeExplosion, 5);
                addExplosion(explosion, listExplosion);
                break;
            }

            explosion = new Explosion(this.worldX, this.worldY + i * gp.tileSize, Constants.timeExplosion, 4);
            addExplosion(explosion, listExplosion);
        }

    }

    /**
     * tao them 1 power_up.
     */
    public void createObject(int x, int y) {
        if (gp.tileM.mapPowerUp[x][y] == -1) {
            return;
        }

        //bomb
        if (gp.tileM.mapPowerUp[x][y] == 0) {
            PowerUp_Bombs tmp = new PowerUp_Bombs(x * gp.tileSize, y * gp.tileSize);

            gp.tileM.mapPowerUp[x][y] = -1;
            gp.listPowerUp.add(tmp);
        }

        //flame
        if (gp.tileM.mapPowerUp[x][y] == 1) {
            PowerUp_Flames tmp = new PowerUp_Flames(x * gp.tileSize, y * gp.tileSize);

            gp.tileM.mapPowerUp[x][y] = -1;
            gp.listPowerUp.add(tmp);
        }

        //speed
        if (gp.tileM.mapPowerUp[x][y] == 2) {
            PowerUp_Speed tmp = new PowerUp_Speed(x * gp.tileSize, y * gp.tileSize);

            gp.tileM.mapPowerUp[x][y] = -1;
            gp.listPowerUp.add(tmp);
        }

        //door
        if (gp.tileM.mapPowerUp[x][y] == 3) {
            Door tmp = new Door(x * gp.tileSize, y * gp.tileSize);

            gp.tileM.mapPowerUp[x][y] = -1;
            gp.listPowerUp.add(tmp);
        }
    }

    /**
     * them Explosion vao list.
     */
    public void addExplosion(Explosion explosion, ArrayList<Explosion> listExplosion) {
        int idx = -1;
        Explosion tmp;

        for (int i = 0; i < listExplosion.size(); ++i) {
            tmp = listExplosion.get(i);

            if (tmp.time <= 0) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            listExplosion.add(explosion);
            tmp = listExplosion.get(listExplosion.size() - 1);
            return;
        }

        listExplosion.set(idx, explosion);
    }

    /**
     * them 1 BrickExplo vao list.
     */
    public void addBrickExplo(ArrayList<BrickExplo> listBrickExplo, BrickExplo newBrickExplo) {
        int idx = -1;
        BrickExplo tmp;

        for (int i = 0; i < listBrickExplo.size(); ++i) {
            tmp = listBrickExplo.get(i);

            if (tmp.time <= 0) {
                idx = i;
                break;
            }
        }

        if (idx != -1) {
            listBrickExplo.set(idx, newBrickExplo);
            return;
        }

        listBrickExplo.add(newBrickExplo);
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
        } else if (i == 2) {
            g2.drawImage(image1, x, y, gp.tileSize, gp.tileSize, null);
        } else if (i == 3) {
            g2.drawImage(image2, x, y, gp.tileSize, gp.tileSize, null);
        }
    }
}
