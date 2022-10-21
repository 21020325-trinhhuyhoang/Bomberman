package Enemy;

import Convert.Convert;
import main.Constants;
import main.GamePanel;
import sun.plugin2.util.ColorUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class SuperEnemy {
    public BufferedImage[][] image;
    public GamePanel gp;
    public BufferedImage imgMenu;

    public SuperEnemy(GamePanel gp, String mapName) {
        image = new BufferedImage[20][30];
        this.gp = gp;

        loadImage();
        loadMap(mapName);
    }

    public void loadImage() {
        try {
            //menu
            imgMenu = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/menu.png"));

            //balloom
            //deadth
            image[0][0] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/balloom_dead.png"));

            /*for (int i = 0; i < image[0][0].getWidth(); ++i) {
                for (int j = 0; j < image[0][0].getHeight(); ++j) {
                    int pixel = image[0][0].getRGB(i,j);
                    int alpha = (pixel >> 24) & 0xff;
                    int red = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue = (pixel >> 0) & 0xff;


                    if (alpha != 0) {
                        int r = red;
                        int g = green;
                        int b = blue;
                        int a = 100;
                        int col = (a << 24) | (r << 16) | (g << 8) | b;

                        image[0][0].setRGB(i, j, col);
                    }
                }
            }*/

            //left1
            image[0][1] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/balloom_left1.png"));

            //left2
            image[0][2] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/balloom_left2.png"));

            //left3
            image[0][3] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/balloom_left3.png"));

            //right1
            image[0][4] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/balloom_right1.png"));

            //right2
            image[0][5] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/balloom_right2.png"));

            //right3
            image[0][6] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/balloom_right3.png"));

            //oneal
            //deadth
            image[1][0] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/oneal_dead.png"));

            //left1
            image[1][1] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/oneal_left1.png"));

            //left2
            image[1][2] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/oneal_left2.png"));

            //left3
            image[1][3] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/oneal_left3.png"));

            //right1
            image[1][4] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/oneal_right1.png"));

            //right2
            image[1][5] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/oneal_right2.png"));

            //right3
            image[1][6] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/oneal_right3.png"));

            //kondoria
            //deadth
            image[2][0] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/kondoria_dead.png"));

            //left1
            image[2][1] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/kondoria_left1.png"));

            //left2
            image[2][2] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/kondoria_left2.png"));

            //left3
            image[2][3] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/kondoria_left3.png"));

            //right1
            image[2][4] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/kondoria_right1.png"));

            //right2
            image[2][5] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/kondoria_right2.png"));

            //right3
            image[2][6] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/kondoria_right3.png"));

            //pontan
            //deadth1
            image[3][0] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/pontan1_dead.png"));

            //1
            image[3][1] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/pontan1.png"));

            //2
            image[3][2] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/pontan1_1.png"));

            //3
            image[3][3] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/pontan1_2.png"));

            //4
            image[3][4] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/pontan1_3.png"));

            //deadth2
            image[3][5] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/pontan2_dead.png"));

            //1
            image[3][6] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/pontan2.png"));

            //2
            image[3][7] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/pontan2_1.png"));

            //3
            image[3][8] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/pontan2_2.png"));

            //4
            image[3][9] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/pontan2_3.png"));

            //minvo
            //deadth
            image[4][0] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/minvo_dead.png"));

            //left1
            image[4][1] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/minvo_left1.png"));

            //left2
            image[4][2] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/minvo_left2.png"));

            //left3
            image[4][3] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/minvo_left3.png"));

            //right1
            image[4][4] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/minvo_right1.png"));

            //right2
            image[4][5] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/minvo_right2.png"));

            //right3
            image[4][6] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/minvo_right3.png"));

            //toxic
            //deadth
            image[5][0] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_dead.png"));

            //left1
            image[5][1] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_left1.png"));

            //left2
            image[5][2] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_left2.png"));

            //left3
            image[5][3] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_left3.png"));

            //right1
            image[5][4] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_right1.png"));

            //right2
            image[5][5] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_right2.png"));

            //right3
            image[5][6] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_right3.png"));

            //doll
            //deadth
            image[6][0] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/doll_dead.png"));

            //left1
            image[6][1] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/doll_left1.png"));

            //left2
            image[6][2] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/doll_left2.png"));

            //left3
            image[6][3] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/doll_left3.png"));

            //right1
            image[6][4] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/doll_right1.png"));

            //right2
            image[6][5] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/doll_right2.png"));

            //right3
            image[6][6] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/doll_right3.png"));

            //FakeBrick
            //deadth
            image[7][0] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/brick_deadth.png"));

            //left1
            image[7][1] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/brick_left1.png"));

            //left2
            image[7][2] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/brick_left2.png"));

            //right1
            image[7][3] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/brick_right1.png"));

            //right2
            image[7][4] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/brick_right2.png"));

            //faking
            image[7][5] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/brick.png"));

        } catch (IOException e) {
            System.out.println("Khong load dc anh enemy!");
            e.printStackTrace();
        }

    }

    /**
     * load enemy from map.
     */
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            /**
             * Doc dong dau tien cua file gom 2 thong tin la chieu cao va chie rong cua level.
             */
            String firstLine = br.readLine();

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();
                Enemy newEnemy;

                for (int i = 0; i < line.length(); ++i) {

                    if (line.charAt(i) == '1') {

                    } else if (line.charAt(i) == '0') {

                    } else if (line.charAt(i) == '2') {

                    } else if (line.charAt(i) == 'q') {
                        newEnemy = new Balloom(col * gp.tileSize, row * gp.tileSize, gp);
                        gp.listEnemy.add(newEnemy);
                    } else if (line.charAt(i) == 'w') {
                        newEnemy = new Oneal(col * gp.tileSize, row * gp.tileSize, gp);
                        gp.listEnemy.add(newEnemy);
                    } else if (line.charAt(i) == 'e') {
                        newEnemy = new Kondoria(col * gp.tileSize, row * gp.tileSize, gp);
                        gp.listEnemy.add(newEnemy);
                    } else if (line.charAt(i) == 'r') {
                        newEnemy = new Pontan(col * gp.tileSize, row * gp.tileSize, gp);
                        gp.listEnemy.add(newEnemy);
                    } else if (line.charAt(i) == 't') {
                        newEnemy = new Minvo(col * gp.tileSize, row * gp.tileSize, gp);
                        gp.listEnemy.add(newEnemy);
                    } else if (line.charAt(i) == 'Q') {
                        newEnemy = new Toxic(col * gp.tileSize, row * gp.tileSize, gp);
                        gp.listEnemy.add(newEnemy);
                    } else if (line.charAt(i) == 'y') {
                        newEnemy = new Doll(col * gp.tileSize, row * gp.tileSize, gp);
                        gp.listEnemy.add(newEnemy);
                    } else if (line.charAt(i) == 'u') {
                        newEnemy = new FBrick(col * gp.tileSize, row * gp.tileSize, gp);
                        gp.listEnemy.add(newEnemy);
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


}
