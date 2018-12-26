package com.view.table;

import com.view.hall.HallController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;


public class TimerController implements Initializable{
    @FXML private BorderPane borderPane;
    @FXML private Label number;
    private double xOffset;
    private double yOffset;
    private static TimerController instance;
    private int count = 3;

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
            TableController.getInstance().getTimerStage().setX(event.getScreenX() + xOffset);
            TableController.getInstance().getTimerStage().setY(event.getScreenY() + yOffset);
        });

        borderPane.setOnMouseReleased(event -> {
            borderPane.setCursor(Cursor.DEFAULT);
        });
        //</editor-fold>
        Thread timerThread = new Thread(()->{
            try{
                while (count != 0){
                    String num = Integer.toString(count);
                    Platform.runLater(()->{ number.setText(num); });
                    count--;
                    Thread.sleep(1000);
                }
                Platform.runLater(()->{ TableController.getInstance().getTimerStage().close(); });
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        timerThread.start();
    }

}
