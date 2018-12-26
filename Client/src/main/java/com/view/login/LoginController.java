package com.view.login;

import com.Game;
import com.view.username.UsernameController;
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
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML private BorderPane borderPane;
    @FXML private TextField hostAddressTF;
    @FXML private TextField portNumberTF;
    private double xOffset;
    private double yOffset;
    private static final int LoginWidth = Game.LoginWidth;
    private static final int LoginHeight = Game.LoginHeight;
    public static UsernameController usernameController;
    private static LoginController instance;
    private Scene scene;


    public LoginController() {
        instance = this;
    }

    public static LoginController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Drag and Drop animation
        //<editor-fold defaultstate="collapsed" desc=" Drag and Drop">
        borderPane.setOnMousePressed(event -> {
            xOffset = Game.getPrimaryStage().getX() - event.getScreenX();
            yOffset = Game.getPrimaryStage().getY() - event.getScreenY();
            borderPane.setCursor(Cursor.CLOSED_HAND);
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
        int startXPoint = rand.nextInt(LoginHeight);
        int startYPoint = rand.nextInt(LoginWidth);
        int direction = rand.nextInt(5) + 1;

        KeyValue moveXAxis = null;
        KeyValue moveYAxis = null;
        Rectangle r1 = null;

        switch (direction){
            case 1 :
                // MOVE LEFT TO RIGHT
                r1 = new Rectangle(0,startYPoint,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), LoginWidth -  sizeOfSqaure);
                break;
            case 2 :
                // MOVE TOP TO BOTTOM
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                moveYAxis = new KeyValue(r1.yProperty(), LoginHeight - sizeOfSqaure);
                break;
            case 3 :
                // MOVE LEFT TO RIGHT, TOP TO BOTTOM
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), LoginWidth -  sizeOfSqaure);
                moveYAxis = new KeyValue(r1.yProperty(), LoginHeight - sizeOfSqaure);
                break;
            case 4 :
                // MOVE BOTTOM TO TOP
                r1 = new Rectangle(startXPoint,LoginWidth-sizeOfSqaure ,sizeOfSqaure,sizeOfSqaure);
                moveYAxis = new KeyValue(r1.xProperty(), 0);
                break;
            case 5 :
                // MOVE RIGHT TO LEFT
                r1 = new Rectangle(LoginHeight-sizeOfSqaure,startYPoint,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), 0);
                break;
            case 6 :
                //MOVE RIGHT TO LEFT, BOTTOM TO TOP
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), LoginWidth -  sizeOfSqaure);
                moveYAxis = new KeyValue(r1.yProperty(), LoginHeight - sizeOfSqaure);
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

    public void loginButtonAction() throws IOException{
        String hostname = hostAddressTF.getText().trim();
        int port = Integer.parseInt(portNumberTF.getText().trim());
        if ((hostname.isEmpty())||(portNumberTF.getText().trim().isEmpty())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a right ip address!");
            alert.setContentText("Please check the host address or port number.");
            alert.showAndWait();
        }else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/username.fxml"));
            Parent window = (Pane) fxmlLoader.load();
            usernameController =  fxmlLoader.getController();

            Game.connect(hostname,port);
            this.scene = new Scene(window);
        }
    }

    public void loginFailure(String message){
        Platform.runLater(()-> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning!");
            alert.setHeaderText(message);
            alert.setContentText("Please check for firewall issues and check if the server is running.");
            alert.showAndWait();
        });
    }

    public void connectionLost(String message){
        Platform.runLater(()-> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connection lost!");
            alert.setHeaderText(message);
            alert.setContentText("Please check for firewall issues and check if the server is running.");
            alert.showAndWait();
            Platform.exit();
            System.exit(-1);
        });
    }

    public void showUsernameScene() {
        Platform.runLater(() -> {
            Stage stage = (Stage) hostAddressTF.getScene().getWindow();
            stage.setResizable(false);
            stage.setWidth(UsernameController.NameWidth);
            stage.setHeight(UsernameController.NameHeight);

            stage.setOnCloseRequest((WindowEvent e) -> {
                Platform.exit();
                System.exit(0);
            });
            stage.setScene(this.scene);
            stage.centerOnScreen();
        });
    }

}
