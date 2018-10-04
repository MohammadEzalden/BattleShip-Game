package com.company;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;


public class GUI extends Application implements Serializable {

    private static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage) {
        GUI.primaryStage = primaryStage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @FXML
    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent p = FXMLLoader.load(getClass().getResource("welcome01.fxml"));
        Scene scene = new Scene(p);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
