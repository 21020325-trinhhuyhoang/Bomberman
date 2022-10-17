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

        if (code == KeyEvent.VK_P || code == KeyEvent.VK_ESCAPE) {

            if (gp.GameState == Constants.playing) {
                gp.GameState = Constants.pause;
            } else {
                gp.GameState = Constants.playing;
                upPressed = false;
                downPressed = false;
                leftPressed = false;
                rightPressed = false;
            }

        }

        if (gp.GameState == Constants.pause) {
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
