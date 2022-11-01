package main;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

import Bombs.Explosion;
import Bombs.SuperExplosion;
import Bombs.SuperToxic;
import Bombs.ExToxic;
import BrickExplo.BrickExplo;
import BrickExplo.SuperBrickExplo;
import Convert.Convert;
import Enemy.Enemy;
import Enemy.SuperEnemy;
import Tile.TileManager;
import entity.Player;
import entity.TextBox;
import object.*;
import Enemy.SuperEDeadth;
import Enemy.EDeadth;
import Enemy.Balloom;
import Enemy.Oneal;
import Enemy.Kondoria;
import Enemy.Pontan;
import Enemy.Minvo;
import Enemy.Toxic;
import Enemy.Doll;
import Enemy.FBrick;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class GamePanel extends JPanel implements Runnable {

    //LENH DIEU CHINH MENU
    public int command = 1;

    //THONG SO GAME
    public int level = 1;
    public int TotalEnemy;
    public int live = 3;
    public boolean music = true;
    public int time;
    public int timeCount;
    public int score;
    public int newscore = 0;
    public int scoreLive;
    public int newScoreLive = 0;
    public int newHighScore = 5;
    public int timeStage = 0;
    public int timeGO = 0;

    //SCREEN SETTINGsss
    public final int originalTileSize = 16; // 16x16 tile
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 17; //16
    public final int maxScreenRow = 13; //12
    public int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public Sound sound = new Sound(this);

    //World setting (hang va cot cua level)
    public int maxWorldCol = 15;
    public int maxWorldRow = 17;

    //FPS
    public int fps = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);
    public ConllisionChecker cCheck;

    public Player player;
    public TileManager tileM;
    public SuperExplosion superExplosion;
    public SuperToxic superToxic;
    public ArrayList<Explosion> listExplosion = new ArrayList<>();
    public ArrayList<ExToxic> listToxic = new ArrayList<>();
    public SuperBrickExplo superBrickExplo = new SuperBrickExplo();
    public ArrayList<BrickExplo> listBrickExplo = new ArrayList<>();
    public ArrayList<Enemy> listEnemy = new ArrayList<>();
    public SuperEnemy se = new SuperEnemy(this, Constants.nameFile);
    public SuperEDeadth sed = new SuperEDeadth(this);
    public ArrayList<EDeadth> listEDeadth = new ArrayList<>();
    public ArrayList<SuperObject> listPowerUp = new ArrayList<>();
    public ArrayList<TextBox> listText = new ArrayList<>();
    public ArrayList<Score> listScore = new ArrayList<>();
    public int GameState = Constants.menu;

    //thong so player
    public int maxFire = 1, maxBomb = 1, speed = 3;

    //HIEN THI TREN MAN HINH
    public UI myUI = new UI(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        keyH = new KeyHandler(this);
        this.addKeyListener(keyH);
        this.player = new Player(this, keyH, maxFire, maxBomb, speed);

        //de phong truong hop bi loi
        TotalEnemy = 0;
        String name = Convert.nameLevel(level);
        tileM = new TileManager(this, name);
        listExplosion = new ArrayList<>();
        listToxic = new ArrayList<>();
        superBrickExplo = new SuperBrickExplo();
        listBrickExplo = new ArrayList<>();
        listEnemy = new ArrayList<>();
        se = new SuperEnemy(this, name);
        sed = new SuperEDeadth(this);
        listEDeadth = new ArrayList<>();
        listPowerUp = new ArrayList<>();
        listText = new ArrayList<>();
    }

    /**
     * choi lai tu dau.
     */
    public void resetAllData() {
        maxBomb = 1;
        maxFire = 1;
        speed = 3;
        level = 1;
        score = 0;
        newscore = 0;
        newScoreLive = 0;
        live = 3;
        reset();
    }

    public void setupGame() {
        sound.playmusic();
        time = Constants.maxTime;
        timeCount = 0;
        score = newscore;
        scoreLive = newScoreLive;
        player.alive = true;
        loadGame();
        loadRanking();

        superExplosion = new SuperExplosion();
        superExplosion.loadImage();
        superToxic = new SuperToxic();
        superToxic.loadImage();

        cCheck = new ConllisionChecker(this);
        this.player = new Player(this, keyH, maxFire, maxBomb, speed);

        TotalEnemy = 0;
        String name = Convert.nameLevel(level);
        tileM = new TileManager(this, name);
        listExplosion = new ArrayList<>();
        listToxic = new ArrayList<>();
        superBrickExplo = new SuperBrickExplo();
        listBrickExplo = new ArrayList<>();
        listEnemy = new ArrayList<>();
        se = new SuperEnemy(this, name);
        sed = new SuperEDeadth(this);
        listEDeadth = new ArrayList<>();
        listPowerUp = new ArrayList<>();
        listText = new ArrayList<>();
    }

    /**
     * bat dau 1 man choi moi.
     */
    public void reset() {
        GameState = Constants.playing;
        time = Constants.maxTime;
        timeCount = 0;
        score = newscore;
        scoreLive = newScoreLive;
        player.alive = true;

        this.player = new Player(this, keyH, maxFire, maxBomb, speed);

        TotalEnemy = 0;
        String name = Convert.nameLevel(level);
        tileM = new TileManager(this, name);
        listExplosion = new ArrayList<>();
        listToxic = new ArrayList<>();
        superBrickExplo = new SuperBrickExplo();
        listBrickExplo = new ArrayList<>();
        listEnemy = new ArrayList<>();
        se = new SuperEnemy(this, name);
        sed = new SuperEDeadth(this);
        listEDeadth = new ArrayList<>();
        listPowerUp = new ArrayList<>();
        listText = new ArrayList<>();
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
                myUI.Render();

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }

            if (GameState == Constants.playing) {
                if (player.alive == false && player.timeDeadth <= 0 && live > 0) {
                    GameState = Constants.retry;
                } else if (player.alive == false && player.timeDeadth <= 0 && live <= 0) {
                    sound.play("gameover");
                    GameState = Constants.gameOver;
                    timeGO = 2 * 60;
                }
            } else
            //dung khi player chet
            if (player.alive == false && player.timeDeadth <= 0 && live > 0) {
                GameState = Constants.retry;
            }
        }

    }

    public void makeToNextLevel() {
        level ++;
        maxFire = player.fire;
        maxBomb = player.maxBombs;
        speed = player.speed;
        newscore = score;
        newScoreLive = scoreLive;
    }

    public void loadGame() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/resouces/save/Save.txt");
            Scanner scanner = new Scanner(fileInputStream);
            level = scanner.nextInt();
            scanner.nextLine();
            maxFire = scanner.nextInt();
            scanner.nextLine();
            maxBomb = scanner.nextInt();
            scanner.nextLine();;
            speed = scanner.nextInt();
            scanner.nextLine();
            newscore = scanner.nextInt();
            score = newscore;
            scanner.nextLine();
            newScoreLive = scanner.nextInt();
            scoreLive = newScoreLive;
            scanner.nextLine();
            live = scanner.nextInt();
            scanner.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void saveGame() {
        try {
            FileWriter fw = new FileWriter("src/resouces/save/Save.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(level + " level");
            bw.newLine();
            bw.write(maxFire + " fire");
            bw.newLine();
            bw.write(maxBomb + " maxBombs");
            bw.newLine();
            bw.write(speed + " speed");
            bw.newLine();
            bw.write(score + " score");
            bw.newLine();
            bw.write(scoreLive + " scoreLive");
            bw.newLine();
            bw.write(live + " live");
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void loadRanking() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/resouces/save/HighScores.txt");
            Scanner scanner = new Scanner(fileInputStream);
            for (int i = 0; i < 5; i++) {
                int scorePlayer = scanner.nextInt();
                String namePlayer = scanner.nextLine();
                listScore.add(new Score(namePlayer.trim(), scorePlayer));
            }
            listScore.add(new Score("", 0));
            scanner.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void saveRanking() {
        try {
            FileWriter fw = new FileWriter("src/resouces/save/HighScores.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < 5; i++) {
                bw.write(String.valueOf(listScore.get(i).score) + " " + listScore.get(i).name);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void Playing() {
        update();
        repaint();
    }

    public void Pause() {
        repaint();
    }

    public void Stage() {
        timeStage --;
        if (timeStage <= 0) {
            timeStage = 0;
            GameState = Constants.playing;
        }
        repaint();
    }

    public void gameOver() {
        timeGO --;
        if (timeGO <= 0) {
            timeGO = 0;
            if (score >= listScore.get(4).score) {
                listScore.set(5, new Score("", score));
                for (int i = 5; i > 0; i--) {
                    if (listScore.get(i).score >= listScore.get(i - 1).score) {
                        newHighScore = i - 1;
                        Score temp = listScore.get(i);
                        listScore.set(i, listScore.get(i - 1));
                        listScore.set(i - 1, temp);
                    } else {
                        break;
                    }
                }
                resetAllData();
                GameState = Constants.newHighScore;
            } else {
                resetAllData();
                GameState = Constants.menu;
            }
        }
        repaint();
    }

    public void Menu() {
        repaint();
    }

    public void Ranking() {
        repaint();
    }

    public void NewHighScore() {
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

        //update Toxic
        updateToxic();

        //update BrickExplo
        updateBrickExplo();

        //update Enemy
        updateEnemy();

        //update TextBox
        updateTextBox();

    }

    public void updateTextBox() {
        for (int i = 0; i < listText.size(); ++i) {
            TextBox tmp = listText.get(i);

            if (tmp.time > 0) {
                listText.get(i).updateText();
            }
        }
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
     * xu ly toxic.
     */
    public void updateToxic() {
        ExToxic tmp;
        for (int i = 0; i < listToxic.size(); ++i) {
            tmp = listToxic.get(i);

            if (tmp.time > 0) {
                tmp.time--;

                //Huy gia tri vu no
                if (tmp.time == 0) {
                    int x = tmp.worldX / tileSize;
                    int y = tmp.worldY / tileSize;

                    tileM.mapToxic[x][y]--;
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
                        TotalEnemy --;
                        score += tmp.score;
                        scoreLive += tmp.score;

                        TextBox newTextBox;

                        if (scoreLive >= 5000) {
                            live = live + scoreLive / 5000;
                            scoreLive = scoreLive % 5000;
                            newTextBox = new TextBox(player.worldX, player.worldY + tileSize / 2 - 20, 121, "Life Up!",this);
                            addTextBox(newTextBox);
                        }

                        newTextBox = new TextBox(tmp.worldX, tmp.worldY + tileSize / 2 - 20, 121, "" + tmp.score,this);
                        addTextBox(newTextBox);

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
                        if (tmp instanceof Toxic) {
                            type = 5;
                            Toxic newToxic = (Toxic) tmp;
                            newToxic.ToxicExplosion(listToxic);
                        }
                        if (tmp instanceof Doll) {
                            type = 6;
                        }
                        if (tmp instanceof FBrick) {
                            type = 7;
                        }
                        newED = new EDeadth(tmp.worldX, tmp.worldY, type, this);
                        listEDeadth.add(newED);
                        if (music == true) sound.play("enemydeadth");
                    }
                }
                else if (checkColPlayer == true && player.alive == true) {
                    player.alive = false;
                    player.timeDeadth = Constants.timeDeadth;
                    if (music == true) sound.play("playerdeadth");
                    live --;
                }

                if (tmp.hitPoint > 0) {
                    tmp.move();
                }
            }
        }
    }

    /**
     * them 1 textBOx.
     */
    public void addTextBox(TextBox newTextBox) {
        int idx = -1;

        for (int i = 0; i < listText.size(); ++i) {
            if (listText.get(i).time <= 0) {
                idx = i;
                break;
            }
        }

        if (idx != -1) {
            listText.set(idx, newTextBox);
        } else {
            listText.add(newTextBox);
        }
    }

    /**
     * update Object.
     */
    public void updateObject() {
        for (int i = 0; i < listPowerUp.size(); ++i) {
            SuperObject tmp = listPowerUp.get(i);

            if (tmp instanceof Door) {
                boolean collision = tmp.check(this);

                if (collision == true && TotalEnemy == 0) {
                    GameState = Constants.nextLevel;
                }
                continue;
            }

            if (tmp.worldX > 0) {

                //taken by player
                boolean collision = tmp.check(this);

                if (collision == true) {
                    if (music == true) sound.play("item");
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

                //destroy
                boolean explo = tmp.checkExplo(this);

                if (explo == true) {
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

        //Toxic
        paintToxic(g2);

        //Brick Explo
        paintBrickExplosion(g2);

        //Enemy Deadth
        paintEDeadth(g2);

        //Enemy
        paintEnemy(g2);

        //Bomberman
        player.draw(g2);

        //TextBox
        paintTextBox(g2);

        //Draw live
        drawBox(g2);


        if (GameState == Constants.pause) {
            myUI.drawPauseScreen(g2);
        }

        if (GameState == Constants.menu) {
            myUI.drawMenu(g2, se);
        }

        if (GameState == Constants.stage) {
            myUI.drawStage(g2);
        }

        if (GameState == Constants.gameOver) {
            myUI.drawGameOver(g2);
        }

        if (GameState == Constants.ranking) {
            myUI.drawRanking(g2);
        }

        if (GameState == Constants.newHighScore) {
            myUI.drawNewHighScore(g2);
        }

        g2.dispose();
    }

    /**
     * ve textBox.
     */
    void paintTextBox(Graphics2D g2) {
        for (int i = 0; i < listText.size(); ++i) {
            if (listText.get(i).time > 0) {
                listText.get(i).renderText(g2,myUI);
            }
        }
    }

    /**
     * ve hop live.
     */
    void drawBox(Graphics2D g2) {
        g2.setColor(new Color(250, 250, 250, 150));
        g2.drawRoundRect(0,1,tileSize * 11,tileSize + tileSize * 1 / 2,15,15);
        g2.drawRoundRect(3,4,tileSize * 11 - 6,tileSize + tileSize * 1 / 2 - 6,15,15);
        g2.fillRoundRect(0,1,tileSize * 11,tileSize + tileSize * 1 / 2,15,15);

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(3,4,tileSize * 11 - 6,tileSize + tileSize * 1 / 2 - 6,15,15);

        g2.setColor(new Color(0,0,0,100));
        g2.drawImage(player.down,2,13,tileSize,tileSize,null);

        g2.setFont(myUI.maruMonica);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 55F));
        g2.setColor(Color.WHITE);
        String text = " x " + live;
        g2.drawString(text, 2 + tileSize - tileSize / 4, 55);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
        text = "TIME: " + time;
        g2.drawString(text, 2 + tileSize * 3, 55 );

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
        text = "SCORE: " + score;
        g2.drawString(text, 2 + tileSize * 3 * 2 + 20, 55 );
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
     * render Toxic.
     */
    public void paintToxic(Graphics2D g2) {
        ExToxic tmp;

        for (int i = 0; i < listToxic.size(); ++i) {
            tmp = listToxic.get(i);

            if (tmp.time > 0) {
                tmp.draw(g2, this, superToxic);
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