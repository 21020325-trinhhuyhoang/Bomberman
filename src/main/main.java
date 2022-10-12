package main;

import javax.swing.JFrame;

public class main {

    public static int maxFire = 1;

    public static void main(String[] args) {

        GamePanel gamePanel = new GamePanel();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Bomberman");
        window.add(gamePanel);
        window.pack();

        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        Sound.loop("soundtrack1");
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }


}
