package main;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

import Bombs.Explosion;
import Bombs.SuperExplosion;
import Bombs.Bombs;
import BrickExplo.BrickExplo;
import BrickExplo.SuperBrickExplo;
import Enemy.Enemy;
import Enemy.SuperEnemy;
import Tile.TileManager;
import entity.Player;
import object.PowerUp_Bombs;
import object.PowerUp_Flames;
import object.PowerUp_Speed;
import object.SuperObject;
import Enemy.SuperEDeadth;
import Enemy.EDeadth;
import Enemy.Balloom;
import Enemy.AStar;
import Enemy.Oneal;
import Enemy.Kondoria;
import Enemy.Pontan;
import Enemy.Minvo;

public class GamePanel extends JPanel implements Runnable {

    //THONG SO GAME
    public int level = 1;
    public int TotalEnemy;

    //SCREEN SETTING
    public final int originalTileSize = 16; // 16x16 tile
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 17; //16
    public final int maxScreenRow = 13; //12
    public int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //World setting (hang va cot cua level)
    public int maxWorldCol = 15;
    public int maxWorldRow = 17;
    public int worldWidth = tileSize * maxWorldCol;
    public int worldHeight = tileSize * maxWorldRow;

    //FPS
    public int fps = 60;

    Thread gameThread;
    KeyHandler keyH;
    public ConllisionChecker cCheck;

    public Player player;
    public TileManager tileM;
    public AssetSetter aSetter = new AssetSetter(this);

    public SuperExplosion superExplosion;
    public ArrayList<Explosion> listExplosion = new ArrayList<>();
    public SuperBrickExplo superBrickExplo = new SuperBrickExplo();
    public ArrayList<BrickExplo> listBrickExplo = new ArrayList<>();
    public ArrayList<Enemy> listEnemy = new ArrayList<>();
    public SuperEnemy se = new SuperEnemy(this, Constants.nameFile);
    public SuperEDeadth sed = new SuperEDeadth(this);
    public ArrayList<EDeadth> listEDeadth = new ArrayList<>();
    public ArrayList<SuperObject> listPowerUp = new ArrayList<>();
    public int GameState = Constants.playing;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        keyH = new KeyHandler();
        this.addKeyListener(keyH);
        this.player = new Player(this, keyH);

        //de phong truong hop bi loi
        TotalEnemy = 0;
        tileM = new TileManager(this, Constants.nameFile);
        aSetter = new AssetSetter(this);
        listExplosion = new ArrayList<>();
        superBrickExplo = new SuperBrickExplo();
        listBrickExplo = new ArrayList<>();
        listEnemy = new ArrayList<>();
        se = new SuperEnemy(this, Constants.nameFile);
        sed = new SuperEDeadth(this);
        listEDeadth = new ArrayList<>();
        listPowerUp = new ArrayList<>();
    }

    public void setupGame() {
        superExplosion = new SuperExplosion();
        superExplosion.loadImage();
        cCheck = new ConllisionChecker(this);

        TotalEnemy = 0;
        tileM = new TileManager(this, Constants.nameFile);
        aSetter = new AssetSetter(this);
        listExplosion = new ArrayList<>();
        superBrickExplo = new SuperBrickExplo();
        listBrickExplo = new ArrayList<>();
        listEnemy = new ArrayList<>();
        se = new SuperEnemy(this, Constants.nameFile);
        sed = new SuperEDeadth(this);
        listEDeadth = new ArrayList<>();
        listPowerUp = new ArrayList<>();
    }

    /**
     * bat dau 1 man choi moi.
     */
    public void reset() {
        GameState = Constants.playing;
        this.player = new Player(this, keyH);

        TotalEnemy = 0;
        tileM = new TileManager(this, Constants.nameFile);
        aSetter = new AssetSetter(this);
        listExplosion = new ArrayList<>();
        superBrickExplo = new SuperBrickExplo();
        listBrickExplo = new ArrayList<>();
        listEnemy = new ArrayList<>();
        se = new SuperEnemy(this, Constants.nameFile);
        sed = new SuperEDeadth(this);
        listEDeadth = new ArrayList<>();
        listPowerUp = new ArrayList<>();
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

        //vong lap game ket thuc o day
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            //update va ve lai trang thai cua game
            if (delta >= 1) {
                //dang choi
                if (GameState == Constants.playing || GameState == Constants.retry) {
                    Playing();
                }

                if (GameState == Constants.retry) {
                    reset();
                }

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }

            //dung khi player chet
            if (player.alive == false && player.timeDeadth <= 0) {
                //this.gameThread = null;
                GameState = Constants.retry;
            }
        }

    }

    public void Playing() {
        update();
        repaint();
    }

    public void update() {
        //updatePlayer
        player.update();

        keyH.spaceTyped = false;

        //update Bombs
        updateBombs();

        //update Object
        updateObject();

        //update Explosion
        updateExplosion();

        //update BrickExplo
        updateBrickExplo();

        //update Enemy
        updateEnemy();

    }

    //update Bombs
    public void updateBombs() {
        for (int i = 0; i < player.maxBombs; ++i) {
            if (player.arrBombs[i].time > 0) {
                player.arrBombs[i].update(this, player, listExplosion);
            }
        }
    }

    /**
     * xu ly vu no.
     */
    public void updateExplosion() {
        Explosion tmp;
        for (int i = 0; i < listExplosion.size(); ++i) {
            tmp = listExplosion.get(i);

            if (tmp.time > 0) {
                tmp.time--;

                //Huy gia tri vu no
                if (tmp.time == 0) {
                    int x = tmp.worldX / tileSize;
                    int y = tmp.worldY / tileSize;

                    tileM.mapExplosion[x][y]--;
                }
            }

        }

    }

    /**
     * update Brick explo.
     */
    public void updateBrickExplo() {
        BrickExplo tmp;

        for (int i = 0; i < listBrickExplo.size(); ++i) {
            tmp = listBrickExplo.get(i);

            if (tmp.time > 0) {
                tmp.time--;
            }
        }
    }

    /**
     * update enemy.
     */
    public void updateEnemy() {
        Enemy tmp;
        EDeadth newED;
        int type = 0;
        boolean checkDeadth = false;
        boolean checkColPlayer = false;

        for (int i = 0; i < listEnemy.size(); ++i) {
            tmp = listEnemy.get(i);

            if (tmp.hitPoint > 0) {
                checkDeadth = tmp.checkDeadth();
                checkColPlayer = tmp.checkColPlayer();

                if (checkDeadth == true) {
                    tmp.hitPoint--;

                    if (tmp.hitPoint <= 0) {
                        if (tmp instanceof Oneal) {
                            type = 1;
                        }
                        if (tmp instanceof Balloom) {
                            type = 0;
                        }
                        if (tmp instanceof Kondoria) {
                            type = 2;
                        }
                        if (tmp instanceof Pontan) {
                            type = 3;
                            Pontan tmp2 = (Pontan) tmp;
                            if (tmp2.color == 2) {
                                type = Constants.Pontan2Code;
                            }
                        }
                        if (tmp instanceof Minvo) {
                            type = 4;
                        }
                        newED = new EDeadth(tmp.worldX, tmp.worldY, type, this);
                        listEDeadth.add(newED);

                        Sound.play("enemydeadth");
                    }
                }
                else if (checkColPlayer == true && player.alive == true) {
                    player.alive = false;
                    player.timeDeadth = Constants.timeDeadth;
                    Sound.play("enemydeadth");
                }

                if (tmp.hitPoint > 0) {
                    tmp.move();
                }
            }
        }
    }

    /**
     * update Object.
     */
    public void updateObject() {
        for (int i = 0; i < listPowerUp.size(); ++i) {
            SuperObject tmp = listPowerUp.get(i);

            if (tmp.worldX > 0) {

                boolean collision = tmp.check(this);

                if (collision == true) {
                    Sound.play("item");
                    //PW Bombs
                    if (tmp instanceof PowerUp_Bombs) {
                        player.maxBombs++;
                    }

                    //PW Flames
                    if (tmp instanceof PowerUp_Flames) {
                        player.fire++;
                    }

                    //PW Speed
                    if (tmp instanceof PowerUp_Speed) {
                        player.speed++;
                    }

                    tmp = new SuperObject(-1, -1);
                    listPowerUp.set(i, tmp);
                }
            }
        }
    }

    //ve cac thu o day.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //Tile
        tileM.draw(g2);

        //Object
        paintObject(g2);

        //Bombs
        paintBombs(g2);

        //Explosion
        paintExplosion(g2);

        //Brick Explo
        paintBrickExplosion(g2);

        //Enemy Deadth
        paintEDeadth(g2);

        //Enemy
        paintEnemy(g2);

        //Bomberman
        player.draw(g2);

        g2.dispose();
    }

    /**
     * render Object.
     */
    public void paintObject(Graphics2D g2) {
        for (int i = 0; i < listPowerUp.size(); ++i) {
            SuperObject tmp = listPowerUp.get(i);

            if (tmp.worldX > 0) {
                tmp.draw(g2, this);
            }
        }
    }

    /**
     * render bombs.
     */
    public void paintBombs(Graphics2D g2) {
        for (int i = 0; i < player.maxBombs; ++i) {
            if (player.arrBombs[i].time > 0) {
                player.arrBombs[i].draw(g2, this);
            }
        }
    }

    /**
     * render Explosion.
     */
    public void paintExplosion(Graphics2D g2) {
        Explosion tmp;

        for (int i = 0; i < listExplosion.size(); ++i) {
            tmp = listExplosion.get(i);

            if (tmp.time > 0) {
                tmp.draw(g2, this, superExplosion);
            }
        }
    }

    /**
     * render Brick Explo.
     */
    public void paintBrickExplosion(Graphics2D g2) {
        BrickExplo tmp;

        for (int i = 0; i < listBrickExplo.size(); ++i) {
            tmp = listBrickExplo.get(i);

            if (tmp.time > 0) {
                tmp.draw(g2, this, superBrickExplo);
            }
        }
    }

    /**
     * render enemy.
     */
    public void paintEnemy(Graphics2D g2) {
        Enemy tmp;

        for (int i = 0; i < listEnemy.size(); ++i) {
            tmp = listEnemy.get(i);

            if (tmp.hitPoint > 0) {
                tmp.draw(g2, this, se);
            }
        }
    }

    /**
     * render enemy deadth.
     */
    public void paintEDeadth(Graphics2D g2) {
        EDeadth tmp;

        for (int i = 0; i < listEDeadth.size(); ++i) {
            tmp = listEDeadth.get(i);

            if (tmp.time > 0) {
                tmp.draw(g2, se, sed);
            }
        }
    }

}
