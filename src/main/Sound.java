package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[50];

    public Sound() {
        soundURL[0] = getClass().getResource("/resouces/sound/dropbomb.wav");
        soundURL[1] = getClass().getResource("/resouces/sound/enemydeadth.wav");
        soundURL[2] = getClass().getResource("/resouces/sound/explosion1.wav");
        soundURL[3] = getClass().getResource("/resouces/sound/item.wav");
        soundURL[4] = getClass().getResource("/resouces/sound/soundtrack1.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch(Exception e) {

        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}