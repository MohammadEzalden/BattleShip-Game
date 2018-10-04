package com.company;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;


public class welcome05controller  implements EventHandler<MouseEvent> {

    public JFXButton Bexit,sexit,loadgamebut,QLO,BLO,GH;
    public Label lhit2,lmiss2,lhit1,lmiss1;
    public JFXTextField timeoutText,namePlayer,serchtable,loadgametext,serchtable2,gametable2;
    private static String name,RandSmart;
    @FXML
    static int hit1 = 0, hit2 = 0, mis1 = 0, mis2 = 0,idGame,timeout=10;
    @FXML
    public Label LT;
    public TableView<MarkPlayer> table;
    public  TableColumn<MarkPlayer,Integer> cid,ccountgame,cmark;
    public  TableColumn<GameData,Integer> chour,cminute,cid2;
    public TableColumn<MarkPlayer,String> cname,cnameenmy;
    public TableColumn<GameData,String> cnameenmy2,cname2;
    public TableColumn<GameData,String> c30,c31,c32,c33;
    private static ObservableList<MarkPlayer> list= FXCollections.observableArrayList();
    private static ObservableList<GameData> listm= FXCollections.observableArrayList();
    public TableView <GameData>table2,table3;
    public TableColumn ctime;
    private static List<String>listHmishit=new ArrayList<>();
    private static List<String>listHCoordinates=new ArrayList<>();
    private static List<String>listCmishit=new ArrayList<>();
    private static List<String>listCCoordinates=new ArrayList<>();
    GridPane gpaddship;
    @FXML
    public Button smartButton,randomButton,but;
    Grid grid = new Grid();
    HumanPlayer humanPlayer = new HumanPlayer(grid);
    public TextField tf;
    int lengthscounter;
    public GridPane gpgame, gpgame2;
    Boolean countermakestop = false,win=true;
    BorderPane attackwindow6;
    static BattleshipGame battleshipGame = new BattleshipGame();
    @FXML
    private Pane[][] panes = new Pane[Grid.getHeight()][Grid.getHeight()];
    public Label statusLabel;


    public void setGpgame2(GridPane gpgame2) {

        this.gpgame2 = gpgame2;
    }

    public void setGetnextmove(String getnextmove) {
        this.getnextmove = getnextmove;
    }

    public String getGetnextmove() {

        return getnextmove;
    }

    private String getnextmove;

    int[] shiplengths = {5, 4, 3, 3, 2};

    public static BattleshipGame getBattleshipGame() {
        return battleshipGame;
    }

    private void initializeGridwithoutattack(GridPane gp, int numCols, int numRows) {
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.SOMETIMES);
            gp.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            gp.getRowConstraints().add(rowConstraints);
        }
        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                addPanewithoutindex(gp, i, j);
            }
        }
    }

    private void initializeGrid(GridPane gp, int numCols, int numRows) {
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.SOMETIMES);
            gp.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            gp.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                addPane(gp, i, j);
            }
        }
    }

    private void addPane(GridPane gp, int colIndex, int rowIndex) {
       panes[rowIndex][colIndex] = new Pane();
        panes[rowIndex][colIndex].setOnMouseClicked(e -> {
            //enemyButton
            Stage temp1 = (Stage) Bexit.getScene().getWindow();
            temp1.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {

                    try {
                        Save.openQuickSave();
                        Save.QuickSave(battleshipGame);
                        Save.endQuickSave();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.exit(0);

                }
            });
            getnextmove = rowIndex + " " + colIndex;
            listHCoordinates.add("X: "+rowIndex+"   Y: "+colIndex);
            humanPlayer.setNextmove(getnextmove);
            if(RandSmart.equals("Smart")) {
                String[] Spilite = ((SmartComputerPlayer) battleshipGame.players.get(0)).getattack().split("\\s+");
                int AttackCox = Integer.parseInt(Spilite[0]);
                int AttackCoy = Integer.parseInt(Spilite[1]);
                listCCoordinates.add("X: "+AttackCox+"   Y: "+AttackCoy);
                if(humanPlayer.getBattleShipPlayerGrid().matrix[AttackCox][AttackCoy].getState()=='.') {
                    listCmishit.add("Miss");
                }
                else if(humanPlayer.getBattleShipPlayerGrid().matrix[AttackCox][AttackCoy].getState()=='#'
                        ||humanPlayer.getBattleShipPlayerGrid().matrix[AttackCox][AttackCoy].getState()=='*'){
                    listCmishit.add("Hit");
                }
                else listCmishit.add("Hit Mine");
            }
            else {
                listCCoordinates.add(((RandomComputerPlayer) battleshipGame.players.get(0)).getattack());
                String[] Spilite = ((RandomComputerPlayer) battleshipGame.players.get(0)).getattack().split("\\s+");
                int AttackCox = Integer.parseInt(Spilite[0]);
                int AttackCoy = Integer.parseInt(Spilite[1]);
                if(humanPlayer.getBattleShipPlayerGrid().matrix[AttackCox][AttackCoy].getState()=='.') {
                    listCmishit.add("Miss");
                }
                else if(humanPlayer.getBattleShipPlayerGrid().matrix[AttackCox][AttackCoy].getState()=='#'
                        ||humanPlayer.getBattleShipPlayerGrid().matrix[AttackCox][AttackCoy].getState()=='*'){
                    listCmishit.add("Hit");
                }
                else listCmishit.add("Hit Mine");
            }
            try {
                Thread.sleep(150);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            for (int i = 0; i < Grid.getWidth(); i++)
                for (int j = 0; j < Grid.getHeight(); j++)
                    if (i == rowIndex && j == colIndex) {
                        if (!humanPlayer.attackgrid[rowIndex][colIndex]) {

                            if (humanPlayer.isIshitmine()) {
                                panes[rowIndex][colIndex].setStyle("-fx-background-color: yellow ;");
                                statusLabel.setText("Hit MINE");
                                x = 0;
                                hit1+= 3;
                                listHmishit.add("Hit MINE");
                                statusLabel.setFont(new Font(30));
                                statusLabel.setTextFill(Color.valueOf("yellow"));

                            } else {
                                panes[rowIndex][colIndex].setStyle("-fx-background-color: red ;");
                                statusLabel.setText("Miss");
                                listHmishit.add("Miss");
                                x = 0;
                                mis1++;
                                System.out.println("score mis = "+mis1);
                                statusLabel.setFont(new Font(30));
                                statusLabel.setTextFill(Color.valueOf("red"));
                            }
                        } else if (humanPlayer.attackgrid[rowIndex][colIndex]) {

                            statusLabel.setText("Hit");
                            x = 0;
                            listHmishit.add("Hit");
                            hit1++;
                            System.out.println("score hit = "+hit1);
                            statusLabel.setFont(new Font(30));
                            statusLabel.setTextFill(Color.valueOf("green"));

                        }
                        //battleshipGame.getCurrentPlayer().getNextMove().getCoordinates();
                        if(battleshipGame.getCurrentPlayer().getBattleShipPlayerGrid().ships.size()==0){
                            for(int k=0;k<Grid.getWidth();k++)
                                for(int j1=0;j1<Grid.getWidth();j1++){
                                    panes[k][j1].setDisable(true);
                                }
                            if(battleshipGame.getCurrentPlayer().getClass()==HumanPlayer.class){
                                statusLabel.setTextFill(Color.valueOf("red"));
                                statusLabel.setText("You Lose");
                                win=false;
                            }
                            else{
                                statusLabel.setTextFill(Color.valueOf("green"));
                                statusLabel.setText("You win");

                            }
                            for(int a=0;a<Grid.getWidth();a++)
                                for(int aa=0;aa<Grid.getWidth();aa++)
                                    if(battleshipGame.getCurrentPlayer().getBattleShipPlayerGrid().matrix[a][aa].getState()=='@'){
                                        battleshipGame.getCurrentPlayer().getBattleShipPlayerGrid().matrix[a][aa].setState('.');
                                    }
                            for(int a=0;a<Grid.getWidth();a++)
                                for(int aa=0;aa<Grid.getWidth();aa++) {
                                    if (humanPlayer.getBattleShipPlayerGrid().matrix[a][aa].getState() == '@') {
                                        humanPlayer.getBattleShipPlayerGrid().matrix[a][aa].setState('.');
                                    }
                                    if(humanPlayer.getBattleShipPlayerGrid().matrix[a][aa].getState() == '*') {
                                        hit2++;

                                    }

                                }
                            mis2=(mis1+hit1)-hit2;
                            System.out.println("miss2 = "+ mis2);
                            System.out.println("hit2 = "+ hit2);
                             Bexit.setDisable(false);
                            countermakestop=true;

                        }
                    }
        });

        gp.add(panes[rowIndex][colIndex], colIndex, rowIndex);

    }

    private void addPanewithoutindex(GridPane gp, int colIndex, int rowIndex) {
        panes1[rowIndex][colIndex] = new Pane();
        String tempcords = rowIndex + " " + colIndex ;
        if(humanPlayer.BattleShipPlayerGrid.matrix[rowIndex][colIndex].getState() == '#') {
            Ship.getshipsparts getshipsparts = humanPlayer.BattleShipPlayerGrid.gethipsOrientatonHashMap.get(tempcords);
            int i = 0;
            for (i = 0; i <getshipsparts.parts.length ; i++) {
                if(getshipsparts.parts[i])
                    break;
            }
            switch (getshipsparts.Oriant) {
                case "left":
                    mackswitchleft(i , rowIndex, colIndex , getshipsparts);
                    break;
                case "down":
                    mackswitchdown(i , rowIndex, colIndex, getshipsparts);
                    break;
                case "up":
                    mackswitchup(i , rowIndex, colIndex,getshipsparts);
                    break;
                case "right":
                    mackswitchright(i , rowIndex, colIndex, getshipsparts);
                    break;

            }
        }
        else if (humanPlayer.BattleShipPlayerGrid.matrix[rowIndex][colIndex].getState() == '*'){
            panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/redx.png);" +
                    "-fx-background-size: cover;" +
                    " -fx-background-repeat: no-repeat;");
            /*Line line = new Line(0,0,10,10);
            line.setStroke(Color.BLACK);

            panes1[rowIndex][colIndex].getChildren().add(line);*/
        }

        gp.add(panes1[rowIndex][colIndex], colIndex, rowIndex);
    }

    private void mackswitchright(int i, int rowIndex, int colIndex, Ship.getshipsparts getshipsparts) {
        if(i != (getshipsparts.Lenth - 1 ))
            switch (i)
            {
                case 0:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/front.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 270;");
                    break;
                case 1:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/mp1.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 270;");
                    break;
                case 2:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/mp2.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 270;");
                    break;
                case 3:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/mp3.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 270;");
                    break;
                case 4:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/end.png);" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-background-size: 50px 50px;"+
                            "-fx-background-position: center center;"+
                            "-fx-rotate: 270;");
                    break;
                //-fx-background-repeat: no-repeat;
                //-fx-background-position: center 8px;
            }
        else
            panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/end.png);" +
                    " -fx-background-repeat: no-repeat;" +
                    "-fx-background-size: cover;"+
                    //"-fx-background-position: center center;"+
                    "-fx-rotate: 270;");
    }

    private void mackswitchup(int i, int rowIndex, int colIndex, Ship.getshipsparts getshipsparts) {

        if(i != (getshipsparts.Lenth - 1 ))
            switch (i)
            {
                case 0:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/front.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 180;");
                    break;
                case 1:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/mp1.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 180;");
                    break;
                case 2:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/mp2.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 180;");
                    break;
                case 3:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/mp3.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 180;");
                    break;
                case 4:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/end.png);" +
                            "-fx-background-size: 50px 50px;"+
                            "-fx-background-position: center center;"+
                            "-fx-rotate: 180;");
                    break;
            }
        else
            panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/end.png);" +
                    "-fx-background-size: 50px 50px;"+
                    "-fx-background-position: center center;"+
                    "-fx-rotate: 180;");

    }

    private void mackswitchdown(int i, int rowIndex, int colIndex, Ship.getshipsparts getshipsparts) {
        if(i != (getshipsparts.Lenth - 1 ))
            switch (i)
            {
                case 0:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/front.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 0;");
                    break;
                case 1:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/mp1.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 0;");
                    break;
                case 2:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/mp2.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 0;");
                    break;
                case 3:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/mp3.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 0;");
                    break;
                case 4:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/end.png);" +
                            "-fx-background-size: 50px 50px;"+
                            "-fx-background-position: center center;"+
                            "-fx-rotate: 0;");
                    break;
            }
        else
            panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/end.png);" +
                    "-fx-background-size: 50px 50px;"+
                    "-fx-background-position: center center;"+
                    "-fx-rotate: 0;");
    }

    private void mackswitchleft(int i , int rowIndex , int colIndex, Ship.getshipsparts getshipsparts) {
        if(i != (getshipsparts.Lenth - 1 ))
            switch (i)
            {
                case 0:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/front.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 90;");
                    break;
                case 1:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/mp1.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 90;");
                    break;
                case 2:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/mp2.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 90;");
                    break;
                case 3:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/mp3.png);" +
                            "-fx-background-size: cover;" +
                            " -fx-background-repeat: no-repeat;" +
                            "-fx-rotate: 90;");
                    break;
                case 4:
                    panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/end.png);" +
                            "-fx-background-size: 50px 50px;"+
                            "-fx-background-position: center center;"+
                            "-fx-rotate: 90;");
                    break;
            }
        else
            panes1[rowIndex][colIndex].setStyle("-fx-background-image: url(com/company/end.png);" +
                    "-fx-background-size: 50px 50px;"+
                    "-fx-background-position: center center;"+
                    "-fx-rotate: 90;");

    }

    Button b[][];

    public void toggleAll(Grid grid) {
        for (Button[] i : b) {
            for (Button j : i) {
                if (j != null)
                    j.setDisable(!j.isDisabled());
            }
        }
        updateButtons(grid);
    }

    public void updateButtons(Grid grid) {
        for (int i = 0; i < grid.matrix.length; i++) {
            for (int j = 0; j < grid.matrix[i].length; j++) {
                if (grid.matrix[i][j].getState() != '.') {
                    b[i][j].getStylesheets().add("com/company/style02.css");
                    b[i][j].setDisable(true);

                }
            }
        }
    }

    public void setDimLabel() throws IOException {
        Stage temp1 = (Stage) but.getScene().getWindow();
        if(!timeoutText.getText().isEmpty())
        timeout=Integer.parseInt(timeoutText.getText());
        Grid.setHeight(Integer.parseInt(tf.getText()));
        Grid.setWidth(Integer.parseInt(tf.getText()));

        GridPane choosePane = FXMLLoader.load(getClass().getResource("welcome03.fxml"));
        Stage chooseStage = new Stage();
        chooseStage.setScene(new Scene(choosePane));
        chooseStage.setTitle("Choose Computer");
        chooseStage.setResizable(false);
        name=namePlayer.getText();
        temp1.close();
        chooseStage.show();

    }

    public void fillShip(Grid grid, int i, int j) {

        toggleAll(grid);
        //Label l= new Label(Integer.toString(shiplengths[lengthscounter]));
        final Pane popup = new Pane(); //popup.setX(300); popup.setY(200);
        popup.setPrefSize(200, 200);
        //l.setTranslateY(100);
        //l.setTranslateX(100);
        Button orientaions[] = {new Button("Left"), new Button("Right"), new Button("Up"), new Button("Down")};
        orientaions[3].setTranslateY(160);
        orientaions[2].setTranslateX(75);
        orientaions[2].setTranslateY(10);
        orientaions[3].setTranslateX(75);
        orientaions[0].setTranslateY(popup.getPrefWidth() / 2 - 20);
        orientaions[1].setTranslateY(popup.getPrefWidth() / 2 - 20);
        orientaions[0].setTranslateX(10);
        orientaions[1].setTranslateX(140);
        Stage stage = new Stage();
        Scene scene = new Scene(popup);
        stage.setScene(scene);
        for (Button c :
                orientaions) {
            c.setPrefSize(50, 2);
            c.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    (new Ship(shiplengths[lengthscounter++])).addShip(grid, i, j, c.getText().toLowerCase());

                    stage.close();
                    toggleAll(grid);
                    updateButtons(grid);
                    if (lengthscounter == shiplengths.length) {
                        ((Stage) b[0][0].getScene().getWindow()).close();
                        try {
                            load06();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        /*Grid.setHeight(Integer.parseInt(tf2.getText()));
        Grid.setWidth(Integer.parseInt(tf1.getText()));*/
        // popup.getChildren().add(l);
        for (int k = 0; k < orientaions.length; k++) {
            if ((new Ship(shiplengths[lengthscounter])).vacant(grid, i, j, orientaions[k].getText().toLowerCase())) {
                popup.getChildren().add(orientaions[k]);
            }
        }
        Label testl = new Label("Ship Length: " + Integer.toString(shiplengths[lengthscounter]));
        popup.getChildren().add(testl);
        testl.setTranslateX(60);
        testl.setTranslateY(100);
        stage.setResizable(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                toggleAll(grid);
            }
        });
        stage.show();

    }

    private void load06() throws IOException {
        BorderPane attackwindow = FXMLLoader.load(getClass().getResource("welcome06game.fxml"));

        HBox hBox = (HBox) attackwindow.getChildren().get(0);
        VBox vBox = (VBox) hBox.getChildren().get(0);
        Label label=(Label)vBox.getChildren().get(1);
        LT=label;
        JFXButton b1=(JFXButton)vBox.getChildren().get(2);
        Bexit=b1;
        Bexit.setDisable(true);
        GridPane gridPane = (GridPane) vBox.getChildren().get(0);
         gridPane.setStyle("-fx-background-color: #87CEFA;");
         gridPane.setGridLinesVisible(true);

        gpgame =gridPane;
        initializeGrid(gridPane, Grid.getHeight(), Grid.getWidth());
        VBox vBox1 = (VBox) hBox.getChildren().get(1);
        GridPane gridPane1 = (GridPane) vBox1.getChildren().get(0);
        gridPane1.setStyle("-fx-background-color: #87CEFA;");
        gridPane1.setGridLinesVisible(true);

        initializeGridwithoutattack(gridPane1, Grid.getHeight(), Grid.getWidth());
        setGpgame2(gridPane1);

        VBox vBox2 = (VBox) vBox1.getChildren().get(1);
        Label label1 =(Label) vBox2.getChildren().get(0);
        statusLabel = label1;

        getBattleshipGame().subscribe(humanPlayer);
        humanPlayer.getBattleShipPlayerGrid().startMines();

        Stage stage = new Stage();
        stage.setScene(new Scene(attackwindow));
        ((Stage) b[0][0].getScene().getWindow()).close();
        updategpgame2Gridpane();
        updatecoumputerGridpane();
        turncounter();
        stage.show();
        Thread t1 = new Thread((Runnable)getBattleshipGame());
        t1.start();
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            for (int i = 0; i < Grid.getWidth(); i++) {
                for (int j = 0; j < Grid.getWidth(); j++)
                    if (event.getSource() == b[i][j]) {
                        fillShip(grid, i, j);
                    }
            }

        }

    }

    public void setRandom(ActionEvent actionEvent) throws IOException {
        gpaddship = FXMLLoader.load(getClass().getResource("welcome05addship.fxml"));
        Stage temp1 = (Stage) randomButton.getScene().getWindow();
        temp1.close();
        RandSmart="Random";
        CPUPlayerGenerator cpuplayer = new CPUPlayerGenerator();
        getBattleshipGame().subscribe(cpuplayer.nextPlayer("2"));
        b = new Button[Grid.getWidth()][Grid.getWidth()];
        for (int i = 0; i < Grid.getWidth(); i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100.0 / Grid.getWidth());
            col.setFillWidth(true);
            gpaddship.getColumnConstraints().add(col);
        }
        for (int i = 0; i < Grid.getWidth(); i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / (Grid.getWidth()));
            row.setFillHeight(true);
            gpaddship.getRowConstraints().add(row);
        }

        for (int i = 0; i < Grid.getWidth(); i++) {
            for (int j = 0; j < Grid.getWidth(); j++) {
                b[i][j] = new Button("");
                b[i][j].setMinWidth(700 / Grid.getWidth());
                b[i][j].setMinHeight(500 / Grid.getWidth());
                gpaddship.add(b[i][j], j, i);

            }
        }
        for (int i = 0; i < Grid.getWidth(); i++)
            for (int j = 0; j <Grid.getWidth(); j++)
                b[i][j].setOnMouseClicked(this);
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(gpaddship);
        stage1.setTitle("Grid Size");
        stage1.setScene(scene1);
        stage1.setResizable(false);
        temp1.close();
        stage1.show();
    }

    public void setSmart(ActionEvent actionEvent) throws IOException {
        gpaddship = FXMLLoader.load(getClass().getResource("welcome05addship.fxml"));
        Stage temp1 = (Stage) smartButton.getScene().getWindow();
        CPUPlayerGenerator cpuplayer = new CPUPlayerGenerator();
        RandSmart="Smart";
        getBattleshipGame().subscribe(cpuplayer.nextPlayer("1"));
        System.out.println("cpu grid :");
        gpaddship.setVgap(3);
        b = new Button[Grid.getWidth()][Grid.getWidth()];
        for (int i = 0; i < Grid.getWidth(); i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100.0 / Grid.getWidth());
            col.setFillWidth(true);
            gpaddship.getColumnConstraints().add(col);
        }
        for (int i = 0; i < Grid.getWidth(); i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / (Grid.getWidth()));
            row.setFillHeight(true);
            gpaddship.getRowConstraints().add(row);
        }

        for (int i = 0; i < Grid.getWidth(); i++) {
            for (int j = 0; j < Grid.getWidth(); j++) {
                b[i][j] = new Button("");
                b[i][j].setMinWidth(700 / Grid.getWidth());
                b[i][j].setMinHeight(500 / Grid.getWidth());
                b[i][j].getStylesheets().add("com/company/style01.css");
                gpaddship.add(b[i][j], j, i);

            }
        }
        for (int i = 0; i < Grid.getWidth(); i++)
            for (int j = 0; j < Grid.getWidth(); j++)
                b[i][j].setOnMouseClicked(this);
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(gpaddship);
        stage1.setTitle("Grid Size");
        stage1.setScene(scene1);
        stage1.setResizable(false);
        temp1.close();
        stage1.show();
    }

    int x=0;

    Pane [][] panes1 = new Pane[Grid.getWidth()][Grid.getHeight()] ;

    public void updategpgame2Gridpane( ) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception
            {

                while (true) {

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            //if((battleshipGame.getWhilecounter()%10)==0)x++;
                            //LT.setText((x%11)+"");
                            for (int i = 0; i < humanPlayer.BattleShipPlayerGrid.matrix.length; i++) {
                                for (int j = 0; j < humanPlayer.BattleShipPlayerGrid.matrix[i].length; j++) {

                                    // panes1[i][j].setStyle("-fx-border: 2;");
                                    if (humanPlayer.BattleShipPlayerGrid.matrix[i][j].getState() == '*' && panes1[i][j].getChildren().isEmpty()){
                                   /* Image image = new Image("com/company/destroyedfront.png");
                                    ImageView imageView = new ImageView(image);
                                    imageView.setFitHeight(panes1[i][j].getHeight());
                                    imageView.setFitWidth(panes1[i][j].getWidth());*/

                                        panes1[i][j].setStyle(panes1[i][j].getStyle() +"-fx-background-image: url(com/company/redx.png);" +
                                                        "-fx-background-size: cover;" +
                                                        " -fx-background-repeat: no-repeat;");

                                        //Pane pane = new Pane();
                                    /*FlowPane flowPane = new FlowPane();
                                    flowPane.setStyle("-fx-background-image: url(com/company/redx.png);");
                                    flowPane.setPrefWidth(panes1[i][j].getWidth());
                                    flowPane.setPrefHeight(panes1[i][j].getHeight());

                                    panes1[i][j].getChildren().add(flowPane);*/



                                        // panes1[i][j].getChildren().add(pane);*/
                                    }
                                    else if (humanPlayer.BattleShipPlayerGrid.matrix[i][j].getState() == '@')
                                        panes1[i][j].setStyle("-fx-background-image: url(com/company/mine.png);" +
                                                "-fx-background-size: cover;");
                                    else if (humanPlayer.BattleShipPlayerGrid.matrix[i][j].getState() == '.')
                                        panes1[i][j].setStyle("");
                                    //gpgame2.add(panes1[i][j], i, j);
                                }
                            }
                        }
                    });
                    Thread.sleep(250);
                }
            }

        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

    }

    public void turncounter(){
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception
            {while (true) {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        x++;
                        if(countermakestop)x=0;
                        LT.setText((x%(timeout+1))+"");

                    }
                });
                Thread.sleep(1000);
            }
            }

        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

    }

    public void updatecoumputerGridpane() {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception
            {while (true) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < Grid.getWidth(); i++) {
                            for (int j = 0; j < Grid.getHeight(); j++) {
                                //cell.setStyle("-fx-border: 2;");
                                //cell.setBorder(new Border();
                                if (humanPlayer.attackgrid[i][j] )
                                    panes[i][j].setStyle("-fx-background-color: green ;");
                                else if(!humanPlayer.attackgrid[i][j] && humanPlayer.attackgridviset[i][j])
                                    panes[i][j].setStyle("-fx-background-color: red ;");

                                // gpgame.add(cell, i, j);
                            }
                        }
                    }
                });
                Thread.sleep(150);
            }
            }

        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
   }

    Pane [][]panes2=new Pane[Grid.getWidth()][Grid.getHeight()];
    public void enemyGrid(ActionEvent actionEvent) {
        GridPane cheatPane= new GridPane();
        ComputerPlayer cp=(ComputerPlayer) getBattleshipGame().players.get(0);
        cheatPane.setGridLinesVisible(true);

        for (int i = 0; i < Grid.getHeight(); i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.SOMETIMES);
            cheatPane.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i <cp.BattleShipPlayerGrid.matrix[0].length; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            cheatPane.getRowConstraints().add(rowConstraints);
        }


        // Stage newStage = new Stage();
        for (int i = 0; i < humanPlayer.BattleShipPlayerGrid.matrix.length; i++)
            for (int j = 0; j < humanPlayer.BattleShipPlayerGrid.matrix[i].length;j++){
                panes2[i][j]= new Pane();
                cheatPane.add(panes2[i][j], i, j);                }
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception
            {while (true) {


                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        for(int i=0;i<cp.BattleShipPlayerGrid.matrix.length;i++){
                            for (int j = 0; j < cp.BattleShipPlayerGrid.matrix[i].length; j++) {

                                if(cp.BattleShipPlayerGrid.matrix[j][i].getState()=='*')
                                    panes2[i][j].setStyle("-fx-background-color: black ;");
                                else if(cp.BattleShipPlayerGrid.matrix[j][i].getState()=='#')
                                    panes2[i][j].setStyle("-fx-background-color: grey;");
                                else if(cp.BattleShipPlayerGrid.matrix[j][i].getState()=='@')
                                    panes2[i][j].setStyle("-fx-background-color: yellow;");
                                else
                                    panes2[i][j].setStyle("-fx-background-color: #87CEFA;");
                                //cheatPane.add(cell,i,j);
                            }
                        }
                    }
                });

                Thread.sleep(250);
            }
            }

        };
        Stage stage = new Stage();
        stage.setScene(new Scene(cheatPane,400,400));
        stage.show();
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    public void endgame(ActionEvent actionEvent) throws IOException {

        GridPane GP=FXMLLoader.load(getClass().getResource("welcome07.fxml"));
        Stage temp1 = (Stage) Bexit.getScene().getWindow();

        Label label1=(Label) GP.getChildren().get(1);
        lhit1=label1;
        Label label2=(Label) GP.getChildren().get(3);
        lhit2=label2;
        Label label3=(Label) GP.getChildren().get(4);
        lmiss1=label3;
        Label label4=(Label) GP.getChildren().get(6);
        lmiss2=label4;

        lhit1.setText(hit1+"");
        lhit2.setText(hit2+"");
        lmiss1.setText(mis1+"");
        lmiss2.setText(mis2+"");
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(GP);
        stage1.setTitle("SCORE");
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.show();
        temp1.close();
    }

    public void score(ActionEvent actionEvent) throws IOException, ClassNotFoundException {

        Load.openid();
        idGame=Load.idf()+1;
        Load.endid();
        Save.openid();
        Save.idf(idGame);
        Save.endid();
        int z=0;
        if(win)z=1;
        List<MarkPlayer> listm=new ArrayList<MarkPlayer>();
        /************/
        MarkPlayer markPlayer=null;
        if(idGame!=1){
            Load.openMarkPlayer();
            listm=Load.loadmarkplayer();
            Load.endmarkplayer();
        }
        markPlayer=null;
        for (MarkPlayer m:listm) {
            if(m.getNamePlayer().equals(name)){
                markPlayer=m;
                markPlayer.setCountplay(m.getCountplay()+1);
                if(win)markPlayer.setCountwin(m.getCountwin()+1);
                markPlayer.setMark(markPlayer.getCountwin()/markPlayer.getCountplay());
                listm.set(listm.indexOf(m),markPlayer);
                break;
            }
        }

        if(markPlayer==null){
            listm.add(new MarkPlayer(idGame,z,1,z,name,RandSmart));
        }
        Save.openMarkPlayer();
        Save.SaveMarkPlayer(listm);
        Save.endMarkPlayer();
        Calendar rightNow = Calendar.getInstance();

        GameData gameData=new GameData(idGame,name,RandSmart,
                rightNow.get(Calendar.HOUR_OF_DAY),rightNow.get(Calendar.MINUTE),
                listHmishit,listHCoordinates,
                listCmishit,listCCoordinates);
        Save.openHist();
        Save.saveHist(gameData);
        Save.endHist();




        System.exit(0);
    }
    public void QuickSave(ActionEvent actionEvent) throws IOException {
        Save.openQuickSave();
        Save.QuickSave(battleshipGame);
        Save.endQuickSave();

    }
    public void Savef(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        Load.openid();
        idGame=Load.idf()+1;
        Load.endid();
        Save.openid();
        Save.idf(idGame);
        Save.endid();
        /***********/
        JOptionPane.showMessageDialog(null,"ID Your Game: "+idGame);
        Save.opensavef();
        Save.savef((new GameHistory(hit1,hit2,mis1,mis2,name,idGame,battleshipGame)));
        Save.endsavef();
    }

    public void QuickLoad(ActionEvent actionEvent) throws InterruptedException, IOException, ClassNotFoundException {
        Load.openQuickLoad();
        name=namePlayer.getText();
        battleshipGame =Load.QuickLoad();
        Load.endQuickLoad();
        Stage temp1 = (Stage) QLO.getScene().getWindow();
        humanPlayer=(HumanPlayer)battleshipGame.players.get(1);
        if(battleshipGame.players.get(0).getClass()==RandomComputerPlayer.class)RandSmart="Random";
        else RandSmart="Smart";
        if(!timeoutText.getText().isEmpty())
        timeout=Integer.parseInt(timeoutText.getText());
        Grid.setWidth(battleshipGame.getCurrentPlayer().BattleShipPlayerGrid.matrix.length);
        Grid.setHeight(battleshipGame.getCurrentPlayer().BattleShipPlayerGrid.matrix.length);

        attackwindow6 = FXMLLoader.load(getClass().getResource("welcome06game.fxml"));
        HBox hBox = (HBox) attackwindow6.getChildren().get(0);
        VBox vBox = (VBox) hBox.getChildren().get(0);
        Label label=(Label)vBox.getChildren().get(1);
        LT=label;
        JFXButton b1=(JFXButton)vBox.getChildren().get(2);
        Bexit=b1;
        Bexit.setDisable(true);
        GridPane gridPane = (GridPane) vBox.getChildren().get(0);
        gridPane.setStyle("-fx-background-color: #87CEFA;");
        gridPane.setGridLinesVisible(true);
        panes = new Pane[Grid.getHeight()][Grid.getHeight()];
        panes1 = new Pane[Grid.getWidth()][Grid.getHeight()] ;
        panes2=new Pane[Grid.getWidth()][Grid.getHeight()];
        gpgame =gridPane;
        /***************************/
        initializeGrid(gridPane, Grid.getHeight(), Grid.getWidth());
        VBox vBox1 = (VBox) hBox.getChildren().get(1);
        GridPane gridPane1 = (GridPane) vBox1.getChildren().get(0);
        gridPane1.setStyle("-fx-background-color: #87CEFA;");
        gridPane1.setGridLinesVisible(true);

        initializeGridwithoutattack(gridPane1, Grid.getHeight(), Grid.getWidth());
        setGpgame2(gridPane1);

        VBox vBox2 = (VBox) vBox1.getChildren().get(1);
        Label label1 =(Label) vBox2.getChildren().get(0);
        statusLabel = label1;

        for (int i = 0; i <Grid.getHeight() ; i++) {
            for (int j = 0; j <Grid.getWidth() ; j++) {
                if(humanPlayer.getBattleShipPlayerGrid().matrix[i][j].getState() == '@')
                    humanPlayer.getBattleShipPlayerGrid().matrix[i][j].setState('.');
            }
        }
        for (int i = 0; i <Grid.getHeight() ; i++) {
            for (int j = 0; j <Grid.getWidth() ; j++) {
                if(((ComputerPlayer)battleshipGame.players.get(0)).BattleShipPlayerGrid.matrix[i][j].getState() == '@')
                    ((ComputerPlayer)battleshipGame.players.get(0)).BattleShipPlayerGrid.matrix[i][j].setState('.');
            }
        }
        ((ComputerPlayer)battleshipGame.players.get(0)).BattleShipPlayerGrid.startMines();
        humanPlayer.getBattleShipPlayerGrid().startMines();

        Stage stage = new Stage();
        stage.setScene(new Scene(attackwindow6));

        updategpgame2Gridpane();
        updatecoumputerGridpane();
        turncounter();
        stage.show();
        Thread t1 = new Thread((Runnable)getBattleshipGame());
        t1.start();
        temp1.close();
    }

    public void ViewScores(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        MarkPlayer markPlayer;
        Stage temp1 = (Stage) QLO.getScene().getWindow();
        temp1.hide();
        List<MarkPlayer> listm=new ArrayList<MarkPlayer>();
        AnchorPane AP=FXMLLoader.load(getClass().getResource("Table01.fxml"));
        table=(TableView<MarkPlayer>)AP.getChildren().get(0);

        cid=(TableColumn<MarkPlayer, Integer>)table.getColumns().get(3);
        cmark=(TableColumn<MarkPlayer, Integer>)table.getColumns().get(0);
        ccountgame=(TableColumn<MarkPlayer, Integer>)table.getColumns().get(1);
        cname=(TableColumn<MarkPlayer, String>)table.getColumns().get(4);
        cnameenmy=(TableColumn<MarkPlayer, String>)table.getColumns().get(2);

        cid.setCellValueFactory(new PropertyValueFactory<MarkPlayer,Integer>("idGame"));
        cmark.setCellValueFactory(new PropertyValueFactory<MarkPlayer,Integer>("mark"));
        ccountgame.setCellValueFactory(new PropertyValueFactory<MarkPlayer,Integer>("countplay"));
        cname.setCellValueFactory(new PropertyValueFactory<MarkPlayer,String>("namePlayer"));
        cnameenmy.setCellValueFactory(new PropertyValueFactory<MarkPlayer,String>("typeenmy"));

        /***************/
        Load.openMarkPlayer();

        listm=Load.loadmarkplayer();

        Load.endmarkplayer();
        for (MarkPlayer m:listm) {
            table.getItems().add(m);
            list.add(m);
        }

        Stage stage1 = new Stage();
        Scene scene1 = new Scene(AP);
        stage1.setTitle("SCORE");
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.show();
        stage1.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                temp1.show();
            }
        });
    }

    public void Loadf(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        Pane GP=FXMLLoader.load(getClass().getResource("load01.fxml"));
        Stage temp1 = (Stage) QLO.getScene().getWindow();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(GP);
        stage1.setTitle("Load Game");
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.show();
        temp1.hide();
        if(!timeoutText.getText().isEmpty())
        timeout=Integer.parseInt(timeoutText.getText());
        stage1.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                temp1.show();
            }
        });
    }

    public void GameHistory(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        GameData gameData;
        Stage temp1 = (Stage) GH.getScene().getWindow();
        temp1.hide();
        //List<GameData> listm=new ArrayList<GameData>();
        AnchorPane AP=FXMLLoader.load(getClass().getResource("Table02.fxml"));
        table2=(TableView<GameData>)AP.getChildren().get(0);

        cid2=(TableColumn<GameData, Integer>)table2.getColumns().get(2);
        cname2=(TableColumn<GameData, String>)table2.getColumns().get(3);
        cnameenmy2=(TableColumn<GameData, String>)table2.getColumns().get(1);
        ctime=table2.getColumns().get(0);
        chour=(TableColumn<GameData, Integer>)ctime.getColumns().get(0);
        cminute=(TableColumn<GameData, Integer>)ctime.getColumns().get(1);

        cid2.setCellValueFactory(new PropertyValueFactory<GameData,Integer>("idgame"));
        cname2.setCellValueFactory(new PropertyValueFactory<GameData,String>("nameplayer"));
        cnameenmy2.setCellValueFactory(new PropertyValueFactory<GameData,String>("nameenmy"));
        chour.setCellValueFactory(new PropertyValueFactory<GameData,Integer>("timeh"));
        cminute.setCellValueFactory(new PropertyValueFactory<GameData,Integer>("timem"));

        /***************/
        Load.openHist();

        while (Load.getBis4().available()>0) {
            gameData= Load.loadHist();
            table2.getItems().add(gameData);
            listm.add(gameData);
        }
        Load.endHist();

        Stage stage1 = new Stage();
        Scene scene1 = new Scene(AP);
        stage1.setTitle("SCORE");
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.show();
        stage1.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                temp1.show();
            }
        });
    }

    public void SerchName(KeyEvent touchEvent) {
        serchtable.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(serchtable.textProperty().get().isEmpty()){
                    table.setItems(list);
                    return;
                }

                ObservableList<MarkPlayer> items= FXCollections.observableArrayList();
                ObservableList <TableColumn<MarkPlayer,?>>column =table.getColumns();
                for(int row=0;row<list.size();row++){
                    for(int col=0;col <column.size();col++){
                        TableColumn colVar=column.get(col);
                       String cellvalue= colVar.getCellData(list.get(row)).toString();
                       cellvalue.toLowerCase();
                       if(cellvalue.contains(serchtable.getText().toLowerCase())&&cellvalue.startsWith(serchtable.getText().toLowerCase())){
                            items.add(list.get(row));

                            break;
                       }
                    }
                }

                table.setItems(items);
                
            }
        });
    }

    public void LoadGame(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        //loadgamebut  ___  loadgametext
        GameHistory gameHistory;
        Stage temp1 = (Stage) loadgamebut.getScene().getWindow();
        int id= Integer.parseInt(loadgametext.getText());
        Load.opengames();
        boolean bool=true;
        while (Load.getBos2().available()>0){
            gameHistory=Load.loadgames();
            if(id==gameHistory.getIdGame()){
                battleshipGame=gameHistory.getBattleshipGame();
                bool=false;
                break;
            }
        }
        if(bool){
            JOptionPane.showMessageDialog(null,"NOT FIND ID Enter again");
            return;

        }
        Load.endgames();


        /*********************/

        humanPlayer=(HumanPlayer)battleshipGame.players.get(1);
        if(battleshipGame.players.get(0).getClass()==RandomComputerPlayer.class)RandSmart="Random";
        else RandSmart="Smart";

        Grid.setWidth(battleshipGame.getCurrentPlayer().BattleShipPlayerGrid.matrix.length);
        Grid.setHeight(battleshipGame.getCurrentPlayer().BattleShipPlayerGrid.matrix.length);

        attackwindow6 = FXMLLoader.load(getClass().getResource("welcome06game.fxml"));
        HBox hBox = (HBox) attackwindow6.getChildren().get(0);
        VBox vBox = (VBox) hBox.getChildren().get(0);
        Label label=(Label)vBox.getChildren().get(1);
        LT=label;
        JFXButton b1=(JFXButton)vBox.getChildren().get(2);
        Bexit=b1;
        Bexit.setDisable(true);
        GridPane gridPane = (GridPane) vBox.getChildren().get(0);
        gridPane.setStyle("-fx-background-color: #87CEFA;");
        gridPane.setGridLinesVisible(true);
        panes = new Pane[Grid.getHeight()][Grid.getHeight()];
        panes1 = new Pane[Grid.getWidth()][Grid.getHeight()] ;
        panes2=new Pane[Grid.getWidth()][Grid.getHeight()];
        gpgame =gridPane;
        /***************************/
        initializeGrid(gridPane, Grid.getHeight(), Grid.getWidth());
        VBox vBox1 = (VBox) hBox.getChildren().get(1);
        GridPane gridPane1 = (GridPane) vBox1.getChildren().get(0);
        gridPane1.setStyle("-fx-background-color: #87CEFA;");
        gridPane1.setGridLinesVisible(true);

        initializeGridwithoutattack(gridPane1, Grid.getHeight(), Grid.getWidth());
        setGpgame2(gridPane1);

        VBox vBox2 = (VBox) vBox1.getChildren().get(1);
        Label label1 =(Label) vBox2.getChildren().get(0);
        statusLabel = label1;

        for (int i = 0; i <Grid.getHeight() ; i++) {
            for (int j = 0; j <Grid.getWidth() ; j++) {
                if(humanPlayer.getBattleShipPlayerGrid().matrix[i][j].getState() == '@')
                    humanPlayer.getBattleShipPlayerGrid().matrix[i][j].setState('.');
            }
        }
        for (int i = 0; i <Grid.getHeight() ; i++) {
            for (int j = 0; j <Grid.getWidth() ; j++) {
                if(((ComputerPlayer)battleshipGame.players.get(0)).BattleShipPlayerGrid.matrix[i][j].getState() == '@')
                    ((ComputerPlayer)battleshipGame.players.get(0)).BattleShipPlayerGrid.matrix[i][j].setState('.');
            }
        }
        ((ComputerPlayer)battleshipGame.players.get(0)).BattleShipPlayerGrid.startMines();
        humanPlayer.getBattleShipPlayerGrid().startMines();

        Stage stage = new Stage();
        stage.setScene(new Scene(attackwindow6));

        updategpgame2Gridpane();
        updatecoumputerGridpane();
        turncounter();
        stage.show();
        Thread t1 = new Thread((Runnable)getBattleshipGame());
        t1.start();
        temp1.close();
        /*****************/
    }

    public void entername(KeyEvent keyEvent) {
        namePlayer.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(namePlayer.textProperty().get().isEmpty()){
                    but.setDisable(true);
                    QLO.setDisable(true);
                    BLO.setDisable(true);
                    return;

                }
                but.setDisable(false);
                QLO.setDisable(false);
                BLO.setDisable(false);

            }
        });
    }

    public void SerchNametable2(KeyEvent keyEvent) {
        //serchtable2
        serchtable2.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(serchtable2.textProperty().get().isEmpty()){
                    table2.setItems(listm);
                    return;
                }
                ObservableList<GameData> items= FXCollections.observableArrayList();
                ObservableList <TableColumn<GameData,?>>column =table2.getColumns();
                for(int row=0;row<listm.size();row++){
                    for(int col=1;col <column.size();col++){
                        TableColumn colVar=column.get(col);
                        String cellvalue= colVar.getCellData(listm.get(row)).toString();
                        cellvalue.toLowerCase();
                        if(cellvalue.contains(serchtable2.getText().toLowerCase())&&cellvalue.startsWith(serchtable2.getText().toLowerCase())){
                            items.add(listm.get(row));

                            break;
                        }
                    }
                }

                table2.setItems(items);

            }
        });
    }

    public void Stepbystep(ActionEvent actionEvent) throws IOException {
        //gametable2
        GameData gameData=null;
        int id=Integer.parseInt(gametable2.getText());
        for (GameData g:listm) {
            if(g.getIdgame()==id){
                gameData=g;
                break;
            }
        }
        if(gameData==null){
            JOptionPane.showMessageDialog(null,"NOT FIND ID Enter again");
            return;
        }
        AnchorPane AP=FXMLLoader.load(getClass().getResource("Table03.fxml"));
        table3=(TableView<GameData>)AP.getChildren().get(0);

        c30=(TableColumn<GameData, String>)table3.getColumns().get(0);
        c31=(TableColumn<GameData, String>)table3.getColumns().get(1);
        c32=(TableColumn<GameData, String>)table3.getColumns().get(2);
        c33=(TableColumn<GameData, String>)table3.getColumns().get(3);

        c30.setCellValueFactory(new PropertyValueFactory<GameData,String>("listHCoordinates"));
        c31.setCellValueFactory(new PropertyValueFactory<GameData,String>("listHmishit"));
        c32.setCellValueFactory(new PropertyValueFactory<GameData,String>("listCCoordinates"));
        c33.setCellValueFactory(new PropertyValueFactory<GameData,String>("listCmishit"));

        table3.getItems().add(gameData);







        Stage stage1 = new Stage();
        Scene scene1 = new Scene(AP);
        stage1.setTitle("SCORE");
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.show();
    }
}