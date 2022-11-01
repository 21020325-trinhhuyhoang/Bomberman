package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed = false, downPressed = false, leftPressed = false, rightPressed = false, deadth;

    public boolean spacePressed = false;

    public boolean spaceTyped = false;

    public GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        String s = KeyEvent.getKeyText(code);

        if (gp.GameState == Constants.stage) {
            upPressed = false;
            downPressed = false;
            leftPressed = false;
            rightPressed = false;
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.GameState == Constants.menu) {
                //tiep tuc game (Continue)
                if (gp.command == 1) {
                    upPressed = false;
                    downPressed = false;
                    leftPressed = false;
                    rightPressed = false;
                    gp.GameState = Constants.stage;
                    gp.timeStage = 2 * 60;
                }
                //Game moi (New game)
                else if (gp.command == 2) {
                    upPressed = false;
                    downPressed = false;
                    leftPressed = false;
                    rightPressed = false;
                    gp.resetAllData();
                    gp.saveGame();
                    gp.GameState = Constants.stage;
                    gp.timeStage = 2 * 60;
                }
                //Ranking
                else if (gp.command == 3) {
                    upPressed = false;
                    downPressed = false;
                    leftPressed = false;
                    rightPressed = false;
                    gp.GameState = Constants.ranking;
                }
                //thoat game (EXIT)
                else {
                    System.exit(0);
                }
            }
            //ranking
            else if (gp.GameState == Constants.ranking) {
                gp.GameState = Constants.menu;
            }
        }

        if (gp.GameState == Constants.menu) {

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.command --;
                if (gp.command == 0) gp.command = 3;
                if (gp.music == true) gp.sound.play("button");
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.command ++;
                if (gp.command == 5) gp.command = 1;
                if (gp.music == true) gp.sound.play("button");
            }
        }

        if (code == KeyEvent.VK_P || code == KeyEvent.VK_ESCAPE) {

            if (gp.GameState == Constants.playing) {
                gp.GameState = Constants.pause;
                gp.command = 1;
            }
            else if (gp.GameState == Constants.pause) {
                gp.GameState = Constants.playing;
                upPressed = false;
                downPressed = false;
                leftPressed = false;
                rightPressed = false;
            }

        }

        if (gp.GameState == Constants.pause) {

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.command --;
                if (gp.command == 0) gp.command = 4;
                if (gp.music == true) gp.sound.play("button");
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.command ++;
                if (gp.command == 5) gp.command = 1;
                if (gp.music == true) gp.sound.play("button");
            }

            if (code == KeyEvent.VK_ENTER) {
                //tiep tuc game (BACK)
                if (gp.command == 1) {
                    gp.GameState = Constants.playing;
                    upPressed = false;
                    downPressed = false;
                    leftPressed = false;
                    rightPressed = false;
                }
                //thoat ra menu (MENU)
                else if (gp.command == 2) {
                    gp.GameState = Constants.menu;
                    gp.command = 1;
                }
                //tat/bat am thanh (Music)
                else if (gp.command == 3) {
                    gp.music = !(gp.music);
                }
                //thoat game (EXIT)
                else {
                    System.exit(0);
                }
            }
        }

        if (gp.GameState == Constants.playing) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }

            if (code == KeyEvent.VK_SPACE) {

                if (spacePressed == false) {
                    spaceTyped = true;
                }
                spacePressed = true;
            }
        }

        if (gp.GameState == Constants.newHighScore) {
            String namePlayer = gp.listScore.get(gp.newHighScore).name;
            int scorePlayer = gp.listScore.get(gp.newHighScore).score;

            if (s.length() == 1) {
                if (gp.music == true) gp.sound.play("button");
                namePlayer = namePlayer + s;
            }
            if (code == KeyEvent.VK_SPACE) {
                if (gp.music == true) gp.sound.play("button");
                namePlayer = namePlayer + " ";
            }
            if (code == KeyEvent.VK_BACK_SPACE) {
                if (gp.music == true) gp.sound.play("button");
                namePlayer = namePlayer.substring(0, namePlayer.length() - 1);
            }

            gp.listScore.set(gp.newHighScore, new Score(namePlayer, scorePlayer));

            if (code == KeyEvent.VK_ENTER) {
                if (gp.music == true) gp.sound.play("button");
                gp.saveRanking();
                gp.GameState = Constants.ranking;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        //if (gp.GameState == Constants.playing) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = false;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = false;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if (code == KeyEvent.VK_SPACE) {
                spacePressed = false;
            }
        //}
    }
}