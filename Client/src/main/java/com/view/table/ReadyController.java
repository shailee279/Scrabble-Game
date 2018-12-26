package com.view.table;

import com.Game;
import com.view.hall.HallController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ReadyController implements Initializable {
    //<editor-fold defaultstate="collapsed" desc="//initialize for Ready Scene" >
    @FXML private BorderPane borderPane;
    @FXML public Label title;
    @FXML private VBox vBox;
    @FXML private Button inviteBtn;
    //</editor-fold>
    private double xOffset;
    private double yOffset;
    public static ObservableList<String> data = FXCollections.observableArrayList();
    private boolean toggle = true;
    @FXML private ListView inviteList;
    @FXML private Label playerName;
    @FXML private HBox inviteBox;
    private static ReadyController instance;

    public ReadyController() {
        instance = this;
    }

    public static ReadyController getInstance() {
        return instance;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Drag and Drop animation
        //<editor-fold defaultstate="collapsed" desc=" Drag and Drop">
        borderPane.setOnMousePressed(event -> {
            xOffset = HallController.getStage().getX() - event.getScreenX();
            yOffset = HallController.getStage().getY() - event.getScreenY();
            borderPane.setCursor(Cursor.CLOSED_HAND);
        });

        borderPane.setOnMouseDragged(event -> {
            HallController.getStage().setX(event.getScreenX() + xOffset);
            HallController.getStage().setY(event.getScreenY() + yOffset);
            TableController.getInstance().getReadyStage().setX(event.getScreenX() + xOffset);
            TableController.getInstance().getReadyStage().setY(event.getScreenY() + yOffset);
        });

        borderPane.setOnMouseReleased(event -> {
            borderPane.setCursor(Cursor.DEFAULT);
        });
        //</editor-fold>

        playerName.setText("");
        inviteList.setItems(data);
        inviteList.setVisible(false);
        inviteBox.setVisible(false);
        playerName.textProperty().bind(inviteList.getSelectionModel().selectedItemProperty());
    }

    public void updateInviteList(List<String> playerInHall){
        Platform.runLater(()->{
            data.clear();
            data.addAll(playerInHall);
            inviteList.setItems(data);
        });
    }

    @FXML
    private void ready(){
        TableController.getInstance().getReadyStage().close();
        Game.ready();
    }

    public void invitationRejected(String feedbackMsg){
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invitation Feedback");
            alert.setHeaderText("Invitation Feedback");
            alert.setContentText(feedbackMsg);
            alert.showAndWait();
//            Game.invite();
        });
    }

    @FXML private void confirm(){
        String invitePlayer = playerName.getText();
        if (invitePlayer == null){
            Platform.runLater(()->{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Please choose one player to invite");
                alert.showAndWait();
            });
        }
        else{
            Game.invitePlayer(invitePlayer);
        }
    }

    @FXML
    private void invite(){
        if (toggle){
            inviteBtn.setText("CANCEL");
            vBox.setPadding(new Insets(60,0,0,0));
            inviteList.setVisible(true);
            inviteBox.setVisible(true);
            toggle = false;
            Game.invite();
        }else if (!toggle){
            vBox.setPadding(new Insets(300,0,0,0));
            inviteBtn.setText("INVITE");
            inviteList.setVisible(false);
            inviteBox.setVisible(false);
            toggle = true;
        }
    }

    // return to game hall
    public void returnHall() {
        HallController.getStage().close();
        Game.getPrimaryStage().show();
        Game.returnToHall();
        Game.inGame = false;
    }

    // Minimize Window
    public void minimizeWindow(){
        HallController.getStage().setIconified(true);
        TableController.getInstance().getReadyStage().setIconified(true);
    }
}
