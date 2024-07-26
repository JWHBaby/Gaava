package com.example.dm1;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class Audios {
    private SimpleStringProperty photo;
    private SimpleStringProperty nameTrack;
    private SimpleStringProperty artist;
    private SimpleStringProperty track;

    public Audios(String photo, String nameTrack, String artist, String track) {
        this.photo = new SimpleStringProperty(photo);
        this.nameTrack = new SimpleStringProperty(nameTrack);
        this.artist = new SimpleStringProperty(artist);
        this.track = new SimpleStringProperty(track);
    }

    public Audios() {}

    public String getPhoto() {
        return photo.get();
    }

    public String getNameTrack() {
        return nameTrack.get();
    }

    public String getArtist() {
        return artist.get();
    }

    public String getTrack() {
        return track.get();
    }

    public Connection getDBMusic() {
        String dbUser = "root";
        String dbPass = "";
        String dbServer = "jdbc:mysql://localhost:3306/music1?useUnicode=true&characterEncoding=UTF-8";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbServer, dbUser, dbPass);
        } catch (Exception e) {
            System.out.println("Драйвер на открытие бд недоступен!");
        }
        return null;
    }

    public ObservableList<Audios> loadAudios() {
        ObservableList<Audios> audiosList = FXCollections.observableArrayList();

        try (Connection connection = getDBMusic()) {
            if (connection != null) {
                String query = "SELECT `photo`, `nameTrack`, `artistName`, `audio` FROM `music_playon`";
                try (PreparedStatement statement = connection.prepareStatement(query);
                     ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String photo = resultSet.getString("photo");
                        String nameTrack = resultSet.getString("nameTrack");
                        String artist = resultSet.getString("artistName");
                        String trackFile = resultSet.getString("audio");
                        audiosList.add(new Audios(photo, nameTrack, artist, trackFile));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("В sql запросе есть траблы!");
        }
        return audiosList;
    }
}