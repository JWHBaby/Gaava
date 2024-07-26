package com.example.dm1;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class AudioPlayer {
    File audioFile = new File("aaa.wav");
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
    Clip clip = AudioSystem.getClip();

    private Slider trackSlider;
    private Label timeLabel;

    public AudioPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    }

    public void setTrackSlider(Slider trackSlider) {
        this.trackSlider = trackSlider;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    void tryopen () throws LineUnavailableException, IOException {
        clip.open(audioInputStream);
    }

    void startAudio () {
        clip.start();

        Timer timerSlider = new Timer();
        timerSlider.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (clip != null && clip.isRunning() && trackSlider != null) {
                        trackSlider.setValue((double) clip.getMicrosecondPosition() / clip.getMicrosecondLength());
                    }
                });
            }
        }, 0, 50);
    }

    void stopAudio () {
        clip.stop();
    }
}