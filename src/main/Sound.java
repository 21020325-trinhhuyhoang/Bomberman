package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.BufferedInputStream;

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
                    BufferedInputStream bufferedIn = new BufferedInputStream(getClass().getResourceAsStream("/resouces/sound/" + sound + ".wav"));
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    clip.open(audioStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    public void playmusic() {

        new Thread(new Runnable() {

            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    BufferedInputStream bufferedIn = new BufferedInputStream(getClass().getResourceAsStream("/resouces/sound/soundtrack1.wav"));
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    clip.open(audioStream);
                    while (true) {
                        Thread.sleep(500);
                        if (gp.music == false || gp.GameState >= 5) {
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

        new Thread(new Runnable() {

            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    BufferedInputStream bufferedIn = new BufferedInputStream(getClass().getResourceAsStream("/resouces/sound/soundtrackmenu.wav"));
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    clip.open(audioStream);
                    while (true) {
                        Thread.sleep(500);
                        if (gp.music == false || gp.GameState <= 5) {
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