package main;

import javax.swing.JFrame;

public class main {

    public static int maxFire = 1;

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Bomberman");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //khoi tao loop choi game o day.
        gamePanel.setupGame();
        gamePanel.startGameThread();
        maxFire = gamePanel.player.fire;
    }
}
