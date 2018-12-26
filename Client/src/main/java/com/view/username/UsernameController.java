package com.view.username;

import com.Game;
import com.view.hall.HallController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class UsernameController implements Initializable {

    @FXML private BorderPane borderPane;
    @FXML private TextField usernameTF;
    public static HallController hallController;
    private double xOffset;
    private double yOffset;
    private Scene scene;
    public static final int NameWidth = 600;
    public static final int NameHeight = 400;
    private static UsernameController instance;
    private boolean wait = false;

    public UsernameController() {
        instance = this;
    }

    public static UsernameController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Drag and Drop animation
        //<editor-fold defaultstate="collapsed" desc=" Drag and Drop">
        borderPane.setOnMousePressed(event -> {
            xOffset = Game.getPrimaryStage().getX() - event.getScreenX();
            yOffset = Game.getPrimaryStage().getY() - event.getScreenY();
            borderPane.setCursor(javafx.scene.Cursor.CLOSED_HAND);
        });

        borderPane.setOnMouseDragged(event -> {
            Game.getPrimaryStage().setX(event.getScreenX() + xOffset);
            Game.getPrimaryStage().setY(event.getScreenY() + yOffset);

        });

        borderPane.setOnMouseReleased(event -> {
            borderPane.setCursor(Cursor.DEFAULT);
        });
        //</editor-fold>

        //Background square number settings
        int numberOfSquares = 30;
        while (numberOfSquares > 0){
            generateAnimation();
            numberOfSquares--;
        }
    }

    // Background square animation
    //<editor-fold defaultstate="collapsed" desc=" Background animation">
    /* This method is used to generate the animation on the main window.
     * It will generate random ints to determine
     * the size, speed, starting points and direction of each square.
     */
    public void generateAnimation(){
        Random rand = new Random();
        int sizeOfSqaure = rand.nextInt(50) + 1;
        int speedOfSqaure = rand.nextInt(10) + 5;
        int startXPoint = rand.nextInt(NameHeight);
        int startYPoint = rand.nextInt(NameWidth);
        int direction = rand.nextInt(5) + 1;

        KeyValue moveXAxis = null;
        KeyValue moveYAxis = null;
        Rectangle r1 = null;

        switch (direction){
            case 1 :
                // MOVE LEFT TO RIGHT
                r1 = new Rectangle(0,startYPoint,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), NameWidth -  sizeOfSqaure);
                break;
            case 2 :
                // MOVE TOP TO BOTTOM
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                moveYAxis = new KeyValue(r1.yProperty(), NameHeight - sizeOfSqaure);
                break;
            case 3 :
                // MOVE LEFT TO RIGHT, TOP TO BOTTOM
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), NameWidth -  sizeOfSqaure);
                moveYAxis = new KeyValue(r1.yProperty(), NameHeight - sizeOfSqaure);
                break;
            case 4 :
                // MOVE BOTTOM TO TOP
                r1 = new Rectangle(startXPoint,NameWidth-sizeOfSqaure ,sizeOfSqaure,sizeOfSqaure);
                moveYAxis = new KeyValue(r1.xProperty(), 0);
                break;
            case 5 :
                // MOVE RIGHT TO LEFT
                r1 = new Rectangle(NameHeight-sizeOfSqaure,startYPoint,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), 0);
                break;
            case 6 :
                //MOVE RIGHT TO LEFT, BOTTOM TO TOP
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), NameWidth -  sizeOfSqaure);
                moveYAxis = new KeyValue(r1.yProperty(), NameHeight - sizeOfSqaure);
                break;

            default:
                System.out.println("default");
        }

        r1.setFill(Color.web("#F89406"));
        r1.setOpacity(0.1);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(speedOfSqaure * 1000), moveXAxis, moveYAxis);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        borderPane.getChildren().add(borderPane.getChildren().size()-1,r1);
    }
    //</editor-fold>

    // Terminates Application
    public void closeSystem(){
        Platform.exit();
        System.exit(0);
    }

    // Minimize Window
    public void minimizeWindow(){
        Game.getPrimaryStage().setIconified(true);
    }

    public void randomUsername(){
        usernameTF.setText(Game.randomeUser());
    }

    //action for confirm button
    public void setUsername() throws IOException {
        String username = usernameTF.getText().trim();
        if (username.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter your username!");
            alert.setContentText("Please enter your username or random one on the right.");
            alert.showAndWait();
        }else {
            if (!wait) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/hall.fxml"));
                Parent window = (Pane) fxmlLoader.load();
                hallController = fxmlLoader.getController();
                this.scene = new Scene(window);
                Game.setUsername(username);
                wait = true;
            }
        }
    }

    public void duplicatedUsername(){
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username already exist");
            alert.setContentText("Please change a username.");
            alert.showAndWait();
            wait = false;
        });
    }

    public void showHall() {
        Platform.runLater(() -> {
            Stage stage = (Stage) usernameTF.getScene().getWindow();
            stage.setResizable(false);
            stage.setWidth(HallController.HallWidth);
            stage.setHeight(HallController.HallHeight);
            stage.setScene(this.scene);
            stage.centerOnScreen();
        });
    }
}
