package com.example.dm1;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Page {
    @FXML
    private TableView<Audios> tableView;

    @FXML
    private TableColumn<Audios, String> artistColumn;

    @FXML
    private Circle buttPlay;

    @FXML
    private Button buttonOnPlayOnStop;

    @FXML
    private TableColumn<Audios, String> nameTrackColumn;

    @FXML
    private TableColumn<Audios, String> photoColumn;

    @FXML
    private Button popularButton;

    @FXML
    private TableColumn<Audios, String> trackFileColumn;

    @FXML
    private Slider trackSlider;

    @FXML
    private Label timeLabel;

    private AudioPlayer audioPlayer;

    private boolean isPlaying = false;

    private boolean buttonPressed = false;

    public Page() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        audioPlayer = new AudioPlayer();
        audioPlayer.setTrackSlider(trackSlider);
        audioPlayer.setTimeLabel(timeLabel);
        audioPlayer.tryopen();
    }

    @FXML
    private void initialize() {
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
        nameTrackColumn.setCellValueFactory(new PropertyValueFactory<>("nameTrack"));
        photoColumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
        trackFileColumn.setCellValueFactory(new PropertyValueFactory<>("track"));

        Audios audios = new Audios();
        tableView.setItems(audios.loadAudios());
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        if (!isPlaying) {
            audioPlayer.startAudio();
            buttonOnPlayOnStop.setText("S");
            isPlaying = true;
        } else {
            audioPlayer.stopAudio();
            buttonOnPlayOnStop.setText("P");
            isPlaying = false;
        }
    }

    public void handleSliderChange(MouseEvent mouseEvent) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (audioPlayer.clip != null) {
            long framePosition = (long) (audioPlayer.clip.getFrameLength() * trackSlider.getValue() / 100);
            audioPlayer.clip.setFramePosition((int) framePosition);

            long currentTime = audioPlayer.clip.getMicrosecondPosition() / 1000000;
            String formattedTime = String.format("%02d:%02d", currentTime / 60, currentTime % 60);
            timeLabel.setText(formattedTime);

            if (isPlaying) {
                audioPlayer.clip.start();
                isPlaying = true;
            }
        }
    }
}