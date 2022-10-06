package Enemy;

import Convert.Convert;
import main.Constants;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class SuperEnemy {
    public BufferedImage [][] image;
    public GamePanel gp;

    public SuperEnemy(GamePanel gp) {
        image = new BufferedImage[10][20];
        this.gp = gp;

        loadImage();
        loadMap(Constants.nameFile);
    }

    public void loadImage() {
      try {
          //balloom
          //deadth
          image[0][0] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/balloom_dead.png"));

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

                    }
                    else if (line.charAt(i) == '0') {

                    }
                    else if (line.charAt(i) == '2') {

                    }
                    else if (line.charAt(i) == 'q') {
                        newEnemy = new Balloom(col * gp.tileSize, row * gp.tileSize, gp);
                        gp.listEnemy.add(newEnemy);
                    }
                    else if (line.charAt(i) == 'w') {
                        newEnemy = new Oneal(col * gp.tileSize, row * gp.tileSize, gp);
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
