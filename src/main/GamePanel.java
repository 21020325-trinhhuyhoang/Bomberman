package main;

import javax.swing.JPanel;
import java.awt.*;

import Tile.TileManager;
import entity.Player;
import object.SuperObject;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTING
    public final int originalTileSize = 16; // 16x16 tile
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 17; //16
    public final int maxScreenRow = 13; //12
    public int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //World setting (hang va cot cua level)
    public int maxWorldCol = 17;
    public int maxWorldRow = 13;
    public int worldWidth = tileSize * maxWorldCol;
    public int worldHeight = tileSize * maxWorldRow;

    //FPS
    public int fps = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    public ConllisionChecker cCheck = new ConllisionChecker(this);

    public Player player = new Player(this,keyH);
    public TileManager tileM = new TileManager(this);
    public SuperObject[] obj = new SuperObject[15];
    public AssetSetter aSetter = new AssetSetter(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        //updatePlayer
        player.update();

        keyH.spaceTyped = false;

        //update Bombs
        for (int i = 0; i < player.maxBombs; ++i) {
            if (player.arrBombs[i].time > 0) {
                player.arrBombs[i].update(this,player);
            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //Tile
        tileM.draw(g2);

        //Object
        for (int i = 0; i < obj.length; ++i) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //Bombs
        for (int i = 0; i < player.maxBombs; ++i) {
            if (player.arrBombs[i].time > 0) {
                player.arrBombs[i].draw(g2,this);
            }
        }

        //Bomberman
        player.draw(g2);
        g2.dispose();
    }
}
