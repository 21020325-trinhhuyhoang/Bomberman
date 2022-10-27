package entity;

import main.GamePanel;
import main.UI;
import Convert.PositionScreen;

import java.awt.*;

public class TextBox {
    public GamePanel gp;
    public int x, y, time;
    public String text;

    public TextBox(int x, int y, int time, String text, GamePanel gp) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.text = text;
        this.gp = gp;
    }

    public void updateText() {
        this.time --;
    }

    public void renderText(Graphics2D g2,UI myUI) {
        PositionScreen tmp = PositionScreen.takePos(gp);
        int screenX = x + tmp._x;
        int screenY = y + tmp._y;
        g2.setFont(myUI.maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 33F));
        g2.setColor(Color.WHITE);
        g2.drawString(this.text, screenX, screenY);
    }
}
