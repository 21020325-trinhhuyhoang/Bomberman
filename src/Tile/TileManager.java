package Tile;

import main.GamePanel;
import Convert.Convert;

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
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTitleImage();
        loadMap("/levels/lvl1.txt");
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


        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String firstLine = br.readLine();

            List <Integer> arr = Convert.takeNumberFromString(firstLine);
            Integer worldWidth = arr.get(0);
            Integer worldHeight = arr.get(1);
            gp.maxWorldCol = worldWidth.intValue();
            gp.maxWorldRow = worldHeight.intValue();

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                for (int i = 0; i < line.length(); ++i) {

                    if (line.charAt(i) == '1') {
                        mapTileNum[col][row] = 1;
                    }
                    else if (line.charAt(i) == '0') {
                        mapTileNum[col][row] = 0;
                    }
                    else if (line.charAt(i) == '2') {
                        mapTileNum[col][row] = 2;
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

       while (worldCol < gp.maxScreenCol && worldRow < gp.maxScreenRow) {

           int tileNum = mapTileNum[worldCol][worldRow];

           int worldX = worldCol * gp.tileSize;
           int worldY = worldRow * gp.tileSize;
           int screenX = worldX - gp.player.worldX + gp.player.screenX;
           int screenY = worldY - gp.player.worldY + gp.player.screenY;

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
