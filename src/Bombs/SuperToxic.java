package Bombs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SuperToxic {
    public BufferedImage[][] image;

    public SuperToxic() {
        image = new BufferedImage[10][10];
    }

    public void loadImage() {

        try {
            //0___________________________________
            //center
            image[0][0] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_exploded.png"));

            //no ngang
            image[0][1] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_horizontal.png"));

            //no ngang cuoi trai
            image[0][2] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_horizontal_left_last.png"));

            //no ngang cuoi phai
            image[0][3] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_horizontal_right_last.png"));

            //no doc
            image[0][4] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_vertical.png"));

            //no doc cuoi duoi
            image[0][5] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_vertical_down_last.png"));

            //no doc cuoi tren
            image[0][6] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_vertical_top_last.png"));

            //1_____________________________________

            //center
            image[1][0] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_exploded1.png"));

            //no ngang
            image[1][1] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_horizontal1.png"));

            //no ngang cuoi trai
            image[1][2] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_horizontal_left_last1.png"));

            //no ngang cuoi phai
            image[1][3] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_horizontal_right_last1.png"));

            //no doc
            image[1][4] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_vertical1.png"));

            //no doc cuoi duoi
            image[1][5] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_vertical_down_last1.png"));

            //no doc cuoi tren
            image[1][6] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_vertical_top_last1.png"));

            //2________________________________________________________________
            //center
            image[2][0] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_exploded2.png"));

            //no ngang
            image[2][1] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_horizontal2.png"));

            //no ngang cuoi trai
            image[2][2] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_horizontal_left_last2.png"));

            //no ngang cuoi phai
            image[2][3] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_horizontal_right_last2.png"));

            //no doc
            image[2][4] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_vertical2.png"));

            //no doc cuoi duoi
            image[2][5] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_vertical_down_last2.png"));

            //no doc cuoi tren
            image[2][6] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/toxic_vertical_top_last2.png"));
            //__________________________________________________


        } catch (IOException e) {
            System.out.print("Khong load dc file anh toxic no!");
            e.printStackTrace();
        }

    }
}
