package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Font maruMonica, purisaB;

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/Font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            System.out.println("Loi tao font");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Loi doc file font");
            e.printStackTrace();
        }
    }

    public void Render() {
        //dang choi
        if (gp.GameState == Constants.playing || gp.GameState == Constants.retry) {
            gp.Playing();
        }

        if (gp.GameState == Constants.retry) {
            gp.reset();
        }

        if (gp.GameState == Constants.pause) {
            gp.Pause();
        }

        //man tiep theo
        if (gp.GameState == Constants.nextLevel) {
            gp.makeToNextLevel();
            gp.reset();
        }
    }

    public void drawPauseScreen(Graphics2D g2) {
        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));

        g2.setColor(new Color(0,0,0, 150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        g2.setColor(Color.WHITE);
        //g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text,g2);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text, Graphics2D g2) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
