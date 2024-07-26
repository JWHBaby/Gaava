package com.example.dm1;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;


import static java.awt.Desktop.isDesktopSupported;

public class LoginPage extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Button exitButton = (Button) scene.lookup("#exit");
        Hyperlink hyperlink = (Hyperlink) scene.lookup("#regLink");
        Button enterButton = (Button) scene.lookup("#enterInAccount");
        TextField loginTextfield = (TextField) scene.lookup("#loginField");
        PasswordField passwordField = (PasswordField) scene.lookup("#passField");
        Label label = (Label) scene.lookup("#errorAccount");
        label.setVisible(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();




        enterButton.setOnAction(actionEvent -> {
            DBconnect dBconnect = new DBconnect();
            dBconnect.getlogin(loginTextfield.getText());
            dBconnect.getPass(passwordField.getText());
            try {
                dBconnect.retdata();
                if (dBconnect.checkAccount) {
                    FXMLLoader fxmlLoader1 = new FXMLLoader(Page.class.getResource("page.fxml"));
                    try {
                        Scene scene1 = new Scene(fxmlLoader1.load());
                        stage.hide();
                        stage.setScene(scene1);
                        stage.show();
                        //Audios audios = new Audios();
                        //Aaudios.trackPane();
                    } catch (IOException e) {
                        System.out.println("Что то пошло не так!!!");
                        throw new RuntimeException(e);
                    }
                } else {
                    label.setVisible(true);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });


        exitButton.setOnAction(actionEvent -> stage.close());

        hyperlink.setOnAction(actionEvent -> {
            try{
                if (isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI("http://localhost/GaavaSite/"));
                }
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });

        exitButton.setOnMouseEntered(event -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
                exitButton.setScaleX(1.1);
                exitButton.setScaleY(1.1);
            }), new KeyFrame(Duration.millis(400), e -> {
                exitButton.setScaleX(1.0);
                exitButton.setScaleY(1.0);
            }));   
            timeline.setCycleCount(1);
            timeline.play();
        });

        exitButton.setOnMouseExited(event -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
                exitButton.setScaleX(1.0);
                exitButton.setScaleY(1.0);
            }));
            timeline.setCycleCount(1);
            timeline.play();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}