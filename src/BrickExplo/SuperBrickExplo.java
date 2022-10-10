package BrickExplo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SuperBrickExplo {
    public BufferedImage[] image;

    public SuperBrickExplo() {
        image = new BufferedImage[3];

        loadImage();
    }

    public void loadImage() {
        try {
            this.image[0] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/brick_exploded.png"));
            this.image[1] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/brick_exploded1.png"));
            this.image[2] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/brick_exploded2.png"));
        } catch (IOException e) {
            System.out.println("ko load duoc anh brick explo");
            e.printStackTrace();
        }
    }


}
