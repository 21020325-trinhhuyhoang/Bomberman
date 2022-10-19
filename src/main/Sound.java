package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import main.GamePanel;

public class Sound {
    public GamePanel gp;

    public Sound(GamePanel gp) {
        this.gp = gp;
    }

    public void play(String sound) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/resouces/sound/" + sound + ".wav"));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    public void loop(String sound) {

        new Thread(new Runnable() {

            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/resouces/sound/" + sound + ".wav"));
                    clip.open(inputStream);
                    while (true) {
                        Thread.sleep(500);
                        if (gp.music == false) {
                            clip.stop();
                            Thread.interrupted();
                        } else {
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                            Thread.interrupted();
                        }
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}