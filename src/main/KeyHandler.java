package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

import main.GamePanel;

public class KeyHandler implements KeyListener {

    public boolean upPressed = false, downPressed = false, leftPressed = false, rightPressed = false, deadth;

    public boolean spacePressed = false;

    public boolean spaceTyped = false;

    public GamePanel gp;
    public boolean pausePressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.GameState == Constants.menu) {

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.command --;
                if (gp.command == 0) gp.command = 2;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.command ++;
                if (gp.command == 4) gp.command = 1;
            }

            if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
                //tiep tuc game (Continue)
                if (gp.command == 1) {
                    gp.GameState = Constants.playing;
                    upPressed = false;
                    downPressed = false;
                    leftPressed = false;
                    rightPressed = false;
                }
                //Game moi (New game)
                else if (gp.command == 2) {

                }
                //thoat game (EXIT)
                else {
                    System.exit(0);
                }
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
                if (gp.command == 0) gp.command = 3;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.command ++;
                if (gp.command == 5) gp.command = 1;
            }

            if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
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
                    if (gp.music == true) {
                        gp.stopMusic();
                        gp.music = false;
                    } else {
                        gp.playMusic(4);
                        gp.music = true;
                    }
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.GameState == Constants.playing) {
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
        }
    }


}
