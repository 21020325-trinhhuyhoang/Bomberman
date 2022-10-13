package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Door extends SuperObject {

    public Door(int worldX, int worldY) {
        super(worldX, worldY);
        name = "Door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resouces/sprites/portal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
