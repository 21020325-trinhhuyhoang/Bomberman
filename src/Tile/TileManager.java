package Tile;

import Enemy.Enemy;
import Enemy.Balloom;
import main.Constants;
import main.GamePanel;
import Convert.Convert;
import Convert.PositionScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][], mapBombs[][], mapExplosion[][], mapToxic[][];
    public int mapConllision[][], mapEConllision[][], mapPowerUp[][];

    public TileManager(GamePanel gp, String mapName) {
        this.gp = gp;
        tile = new Tile[30];

        getTitleImage();
        loadMap(mapName);
    }

    public void getTitleImage() {
        try {
            //grass
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/grass.png"));

            //wall
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/wall.png"));
            tile[1].collision = true;

            //brick
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/brick.png"));
            tile[2].collision = true;


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            /**
             * Doc dong dau tien cua file gom 2 thong tin la chieu cao va chie rong cua level.
             */
            String firstLine = br.readLine();

            List<Integer> arr = Convert.takeNumberFromString(firstLine);
            Integer worldWidth = arr.get(0);
            Integer worldHeight = arr.get(1);
            gp.maxWorldCol = worldWidth.intValue();
            gp.maxWorldRow = worldHeight.intValue();

            //System.out.println(gp.maxWorldCol + " " + gp.maxWorldRow);

            mapTileNum = new int[gp.maxWorldCol + 1][gp.maxWorldRow + 1];
            mapBombs = new int[gp.maxWorldCol + 1][gp.maxWorldRow + 1];
            mapToxic= new int[gp.maxWorldCol + 1][gp.maxWorldRow + 1];
            mapConllision = new int[gp.maxWorldCol + 1][gp.maxWorldRow + 1];
            mapExplosion = new int[gp.maxWorldCol + 1][gp.maxWorldRow + 1];
            mapEConllision = new int[gp.maxWorldCol + 1][gp.maxWorldRow + 1];
            mapPowerUp = new int[gp.maxWorldCol + 1][gp.maxWorldRow + 1];

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                for (int i = 0; i < line.length(); ++i) {
                    mapConllision[col][row] = 0;
                    //System.out.println(line.charAt(i));

                    if (line.charAt(i) == '1') {
                        mapTileNum[col][row] = 1;
                        mapConllision[col][row] = 1;
                        mapEConllision[col][row] = 1;
                        mapPowerUp[col][row] = -1;
                    } else if (line.charAt(i) == '0') {
                        mapTileNum[col][row] = 0;
                        mapConllision[col][row] = 0;
                        mapEConllision[col][row] = 0;
                        mapPowerUp[col][row] = -1;
                    } else if (line.charAt(i) == '2') {
                        mapTileNum[col][row] = 2;
                        mapConllision[col][row] = 1;
                        mapEConllision[col][row] = 1;
                        mapPowerUp[col][row] = -1;
                    }
                    //balloom
                    else if (line.charAt(i) == 'q') {
                        mapTileNum[col][row] = 0;
                        mapConllision[col][row] = 0;
                        mapEConllision[col][row] = 0;
                        mapPowerUp[col][row] = -1;
                    }
                    //oneal
                    else if (line.charAt(i) == 'w') {
                        mapTileNum[col][row] = 0;
                        mapConllision[col][row] = 0;
                        mapEConllision[col][row] = 0;
                        mapPowerUp[col][row] = -1;
                    }
                    //kondoria
                    else if (line.charAt(i) == 'e') {
                        mapTileNum[col][row] = 0;
                        mapConllision[col][row] = 0;
                        mapEConllision[col][row] = 0;
                        mapPowerUp[col][row] = -1;
                    }
                    //pontan
                    else if (line.charAt(i) == 'r') {
                        mapTileNum[col][row] = 0;
                        mapConllision[col][row] = 0;
                        mapEConllision[col][row] = 0;
                        mapPowerUp[col][row] = -1;
                    }
                    //minvo
                    else if (line.charAt(i) == 't') {
                        mapTileNum[col][row] = 0;
                        mapConllision[col][row] = 0;
                        mapEConllision[col][row] = 0;
                        mapPowerUp[col][row] = -1;
                    }
                    //toxic
                    else if (line.charAt(i) == 'Q') {
                        mapTileNum[col][row] = 0;
                        mapConllision[col][row] = 0;
                        mapEConllision[col][row] = 0;
                        mapPowerUp[col][row] = -1;
                    }
                    //doll
                    else if (line.charAt(i) == 'y') {
                        mapTileNum[col][row] = 0;
                        mapConllision[col][row] = 0;
                        mapEConllision[col][row] = 0;
                        mapPowerUp[col][row] = -1;
                    }
                    //fbrick
                    else if (line.charAt(i) == 'u') {
                        mapTileNum[col][row] = 0;
                        mapConllision[col][row] = 0;
                        mapEConllision[col][row] = 0;
                        mapPowerUp[col][row] = -1;
                    }
                    //power up bomb
                    else if (line.charAt(i) == 'z') {
                        mapTileNum[col][row] = 2;
                        mapConllision[col][row] = 1;
                        mapEConllision[col][row] = 1;
                        mapPowerUp[col][row] = 0;
                    }
                    //power up flame
                    else if (line.charAt(i) == 'x') {
                        mapTileNum[col][row] = 2;
                        mapConllision[col][row] = 1;
                        mapEConllision[col][row] = 1;
                        mapPowerUp[col][row] = 1;
                    }
                    //power up speed
                    else if (line.charAt(i) == 'c') {
                        mapTileNum[col][row] = 2;
                        mapConllision[col][row] = 1;
                        mapEConllision[col][row] = 1;
                        mapPowerUp[col][row] = 2;
                    }
                    //door
                    else if (line.charAt(i) == 'A') {
                        mapTileNum[col][row] = 2;
                        mapConllision[col][row] = 1;
                        mapEConllision[col][row] = 1;
                        mapPowerUp[col][row] = 3;
                    }

                    col++;
                }

                col = 0;
                row++;
            }

            br.close();

        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            PositionScreen tmp = PositionScreen.takePos(gp);
            int screenX = worldX + tmp._x;
            int screenY = worldY + tmp._y;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }

}
