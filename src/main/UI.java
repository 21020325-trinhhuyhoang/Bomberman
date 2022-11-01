package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import Enemy.SuperEnemy;

public class UI {
    GamePanel gp;
    public Font maruMonica, purisaB;

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

        //game over
        if (gp.GameState == Constants.gameOver) {
            gp.gameOver();
            gp.saveGame();
        }

        //stage
        if (gp.GameState == Constants.stage) {
            gp.Stage();
        }

        //menu
        if (gp.GameState == Constants.menu) {
            gp.Menu();
        }

        //dang choi
        if (gp.GameState == Constants.playing || gp.GameState == Constants.retry) {
            gp.Playing();
        }

        if (gp.GameState == Constants.retry) {
            gp.saveGame();
            gp.reset();
        }

        if (gp.GameState == Constants.pause) {
            gp.Pause();
        }

        //man tiep theo
        if (gp.GameState == Constants.nextLevel) {
            gp.makeToNextLevel();
            gp.saveGame();
            gp.reset();
            gp.GameState = Constants.stage;
            gp.timeStage = 2 * 60;
        }

        if (gp.GameState == Constants.ranking) {
            gp.Ranking();
            System.out.print(1);
        }
    }

    public void drawPauseScreen(Graphics2D g2) {

        g2.setColor(new Color(9, 9, 9, 150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        g2.setColor(new Color(250,250,250,120));
        g2.drawRoundRect(gp.tileSize * 4, gp.tileSize * 2,gp.tileSize * 9, gp.tileSize * 10,15,15);
        g2.drawRoundRect(gp.tileSize * 4 + 3, gp.tileSize * 2 + 3,gp.tileSize * 9 - 6, gp.tileSize * 10 - 6,15,15);
        g2.fillRoundRect(gp.tileSize * 4, gp.tileSize * 2,gp.tileSize * 9, gp.tileSize * 10,15,15);
        g2.setColor(new Color(0, 0, 0, 160));
        g2.fillRoundRect(gp.tileSize * 4 + 3, gp.tileSize * 2 + 3,gp.tileSize * 9 - 6, gp.tileSize * 10 - 6,15,15);

        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));

        g2.setColor(Color.WHITE);
        //g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text,g2);
        int y = gp.screenHeight / 2 - 3 * gp.tileSize;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
        text = "Back";
        y = y + 2 * gp.tileSize;
        g2.drawString(text, x, y);

        if (gp.command == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Menu";
        y = y + 2 * gp.tileSize;
        g2.drawString(text, x, y);

        if (gp.command == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Music: On";
        if (gp.music == false) text = "Music: Off";
        y = y + 2 * gp.tileSize;
        g2.drawString(text, x, y);

        if (gp.command == 3) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Exit";
        y = y + 2 * gp.tileSize;
        g2.drawString(text, x, y);

        if (gp.command == 4) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawStage(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
        String text = "Stage " + gp.level + ": " + Constants.nameStage[gp.level];
        int x = getXforCenteredText(text,g2) - gp.tileSize / 2;
        int y = gp.screenHeight / 2;
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);
    }

    public void drawGameOver(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
        String text = "GAME OVER!";
        int x = getXforCenteredText(text,g2) - gp.tileSize / 2;
        int y = gp.screenHeight / 2 - gp.tileSize;
        g2.setColor(Color.RED);
        g2.drawString(text,x,y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));
        g2.setColor(Color.WHITE);
        text = "SCORE: " + String.valueOf(gp.score);
        x = getXforCenteredText(text,g2) - gp.tileSize / 2;
        y = y + gp.tileSize * 4 / 3;
        g2.drawString(text, x, y);
    }

    public void drawRanking(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 150F));
        String text = "RANKING";
        int x = getXforCenteredText(text,g2) - gp.tileSize / 2;
        int y = gp.screenHeight / 2 - 5 * gp.tileSize;
        g2.setColor(Color.CYAN);
        g2.drawString(text,x,y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));
        g2.setColor(Color.BLUE);
        text = "NAME";
        x = gp.tileSize * 2;
        y = y + gp.tileSize * 3;
        g2.drawString(text, x, y);
        text = "SCORE";
        x =gp.tileSize * 8;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));
        g2.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            text = gp.listScore.get(i).name;
            x = gp.tileSize * 2;
            y = y + gp.tileSize;
            g2.drawString(text, x, y);
            text = String.valueOf(gp.listScore.get(i).score);
            x =gp.tileSize * 8;
            g2.drawString(text, x, y);
        }
    }

    public void drawNewHighScore(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
        String text = "GAME OVER!";
        int x = getXforCenteredText(text,g2) - gp.tileSize / 2;
        int y = gp.screenHeight / 2 - gp.tileSize;
        g2.setColor(Color.RED);
        g2.drawString(text,x,y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));
        g2.setColor(Color.WHITE);
        text = "SCORE: " + String.valueOf(gp.score);
        x = getXforCenteredText(text,g2) - gp.tileSize / 2;
        y = y + gp.tileSize * 4 / 3;
        g2.drawString(text, x, y);
    }

    public void drawMenu(Graphics2D g2, SuperEnemy se) {
        g2.drawImage(se.imgMenu, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        String text = Constants.version;
        g2.drawString(text,4,gp.screenHeight-10);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));
        g2.setColor(Color.WHITE);
        text = "Continue";
        int x = getXforCenteredText(text,g2) - gp.tileSize / 2;
        int y = gp.screenHeight / 2 + 3 * gp.tileSize + gp.tileSize / 8;
        g2.drawString(text, x, y);

        if (gp.command == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "New Game";
        y = y + gp.tileSize;
        g2.drawString(text, x, y);

        if (gp.command == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Ranking";
        y = y + gp.tileSize;
        g2.drawString(text, x, y);

        if (gp.command == 3) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Exit";
        y = y + gp.tileSize;
        g2.drawString(text, x, y);

        if (gp.command == 4) {
            g2.drawString(">", x - gp.tileSize, y);
        }

    }

    public int getXforCenteredText(String text, Graphics2D g2) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
