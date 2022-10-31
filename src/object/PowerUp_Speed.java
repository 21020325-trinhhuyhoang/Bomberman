package object;


import javax.imageio.ImageIO;
import java.io.IOException;

public class PowerUp_Speed extends SuperObject {
    public PowerUp_Speed(int worldX, int worldY) {
        super(worldX, worldY);
        name = "PowerUp_Speed";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/powerup_speed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
