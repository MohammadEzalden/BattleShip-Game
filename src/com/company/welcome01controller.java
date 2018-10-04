package com.company;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.security.sasl.SaslClient;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class welcome01controller {



        @FXML
        private Button chooseGameButton;
        @FXML
        private ImageView battlshipButton;
/*************************************************
    public static Game getBattleshipGame() {
        return  battleshipGame;
    }

    public static Game battleshipGame;*/
    public void openGameChooser(ActionEvent actionEvent) throws IOException {
        Stage welcomeStage = (Stage) chooseGameButton.getScene().getWindow();
        AnchorPane choosePane = FXMLLoader.load(getClass().getResource("welcome02.fxml"));
        Stage sizegridStage = new Stage();
        Scene sizegridScene = new Scene(choosePane);
        sizegridStage.setScene(sizegridScene);
        sizegridStage.setResizable(false);
        sizegridStage.setTitle("Game Choose");
        sizegridStage.show();
        welcomeStage.close();


        try {
            Load.openid();
            Load.endid();
        }catch (IOException e){
            Save.openid();
            Save.idf(0);
            Save.endid();
        }


    }

    public void battleshipSetup(MouseEvent mouseEvent) throws IOException{
            Stage chooseStage = (Stage) battlshipButton.getScene().getWindow();
            Pane dimPane=FXMLLoader.load(getClass().getResource("welcome04.fxml"));
            Stage stage1=new Stage();
            /*********************************/
            /*********************************/
            /*********************************/
            //battleshipGame=new BattleshipGame();
            Scene scene1=new Scene(dimPane);
            stage1.setTitle("Battle Ship Game");
            stage1.setScene(scene1);
            stage1.setResizable(false);
            chooseStage.close();
            stage1.show();

        }

 }




