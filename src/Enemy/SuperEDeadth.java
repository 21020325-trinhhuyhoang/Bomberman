package Enemy;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SuperEDeadth {
    public BufferedImage [] image;
    public GamePanel gp;

    public SuperEDeadth(GamePanel gp) {
        image = new BufferedImage[10];
        this.gp = gp;

        loadImage();
    }

    public void loadImage() {
        try {
            //all
            image[0] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/mob_dead1.png"));
            image[1] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/mob_dead2.png"));
            image[2] = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/mob_dead3.png"));
            
        } catch(IOException e) {
            System.out.println("Khong load duoc file enemy deadth!");
            e.printStackTrace();
        }
    }
}
