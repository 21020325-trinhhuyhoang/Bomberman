package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class PowerUp_Bombs extends SuperObject {

    public PowerUp_Bombs() {
        name = "PowerUp_Bombs";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/powerup_bombs.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
