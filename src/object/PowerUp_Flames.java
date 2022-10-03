package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class PowerUp_Flames extends SuperObject {
    public PowerUp_Flames(int worldX, int worldY) {
        super(worldX, worldY);
        name = "PowerUp_Flames";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/powerup_flames.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
