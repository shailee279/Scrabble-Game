package com.view.table;

import com.Game;
import com.view.hall.HallController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class TableController implements Initializable{

    private static String[] board = new String[400];
    public static final int TableWidth = 1100;
    public static final int TableHeight = 825;
    private double xOffset;
    private double yOffset;
    //<editor-fold defaultstate="collapsed" desc="// initialize for game board" (P.S. this work is exhausting) >
    @FXML private BorderPane borderPane;
    @FXML public Label title;
    @FXML private Label player1Name;
    @FXML private Label player1Score;
    @FXML public Label player1Ready;
    @FXML private ImageView player1Turn;
    @FXML private Label player2Name;
    @FXML private Label player2Score;
    @FXML private Label player2Ready;
    @FXML private ImageView player2Turn;
    @FXML private Label player3Name;
    @FXML private Label player3Score;
    @FXML private Label player3Ready;
    @FXML private ImageView player3Turn;
    @FXML private Label player4Name;
    @FXML private Label player4Score;
    @FXML private Label player4Ready;
    @FXML private ImageView player4Turn;
    @FXML private HBox tileBox;
    @FXML private Button confirmBtn;
    @FXML private Button clearBtn;
    @FXML private Button passBtn;
    @FXML private Button A;
    @FXML private Button B;
    @FXML private Button C;
    @FXML private Button D;
    @FXML private Button E;
    @FXML private Button F;
    @FXML private Button G;
    @FXML private Button H;
    @FXML private Button I;
    @FXML private Button J;
    @FXML private Button K;
    @FXML private Button L;
    @FXML private Button M;
    @FXML private Button N;
    @FXML private Button O;
    @FXML private Button P;
    @FXML private Button Q;
    @FXML private Button R;
    @FXML private Button S;
    @FXML private Button T;
    @FXML private Button U;
    @FXML private Button V;
    @FXML private Button W;
    @FXML private Button X;
    @FXML private Button Y;
    @FXML private Button Z;
    @FXML private TextField T0;
    @FXML private TextField T1;
    @FXML private TextField T2;
    @FXML private TextField T3;
    @FXML private TextField T4;
    @FXML private TextField T5;
    @FXML private TextField T6;
    @FXML private TextField T7;
    @FXML private TextField T8;
    @FXML private TextField T9;
    @FXML private TextField T10;
    @FXML private TextField T11;
    @FXML private TextField T12;
    @FXML private TextField T13;
    @FXML private TextField T14;
    @FXML private TextField T15;
    @FXML private TextField T16;
    @FXML private TextField T17;
    @FXML private TextField T18;
    @FXML private TextField T19;
    @FXML private TextField T20;
    @FXML private TextField T21;
    @FXML private TextField T22;
    @FXML private TextField T23;
    @FXML private TextField T24;
    @FXML private TextField T25;
    @FXML private TextField T26;
    @FXML private TextField T27;
    @FXML private TextField T28;
    @FXML private TextField T29;
    @FXML private TextField T30;
    @FXML private TextField T31;
    @FXML private TextField T32;
    @FXML private TextField T33;
    @FXML private TextField T34;
    @FXML private TextField T35;
    @FXML private TextField T36;
    @FXML private TextField T37;
    @FXML private TextField T38;
    @FXML private TextField T39;
    @FXML private TextField T40;
    @FXML private TextField T41;
    @FXML private TextField T42;
    @FXML private TextField T43;
    @FXML private TextField T44;
    @FXML private TextField T45;
    @FXML private TextField T46;
    @FXML private TextField T47;
    @FXML private TextField T48;
    @FXML private TextField T49;
    @FXML private TextField T50;
    @FXML private TextField T51;
    @FXML private TextField T52;
    @FXML private TextField T53;
    @FXML private TextField T54;
    @FXML private TextField T55;
    @FXML private TextField T56;
    @FXML private TextField T57;
    @FXML private TextField T58;
    @FXML private TextField T59;
    @FXML private TextField T60;
    @FXML private TextField T61;
    @FXML private TextField T62;
    @FXML private TextField T63;
    @FXML private TextField T64;
    @FXML private TextField T65;
    @FXML private TextField T66;
    @FXML private TextField T67;
    @FXML private TextField T68;
    @FXML private TextField T69;
    @FXML private TextField T70;
    @FXML private TextField T71;
    @FXML private TextField T72;
    @FXML private TextField T73;
    @FXML private TextField T74;
    @FXML private TextField T75;
    @FXML private TextField T76;
    @FXML private TextField T77;
    @FXML private TextField T78;
    @FXML private TextField T79;
    @FXML private TextField T80;
    @FXML private TextField T81;
    @FXML private TextField T82;
    @FXML private TextField T83;
    @FXML private TextField T84;
    @FXML private TextField T85;
    @FXML private TextField T86;
    @FXML private TextField T87;
    @FXML private TextField T88;
    @FXML private TextField T89;
    @FXML private TextField T90;
    @FXML private TextField T91;
    @FXML private TextField T92;
    @FXML private TextField T93;
    @FXML private TextField T94;
    @FXML private TextField T95;
    @FXML private TextField T96;
    @FXML private TextField T97;
    @FXML private TextField T98;
    @FXML private TextField T99;
    @FXML private TextField T100;
    @FXML private TextField T101;
    @FXML private TextField T102;
    @FXML private TextField T103;
    @FXML private TextField T104;
    @FXML private TextField T105;
    @FXML private TextField T106;
    @FXML private TextField T107;
    @FXML private TextField T108;
    @FXML private TextField T109;
    @FXML private TextField T110;
    @FXML private TextField T111;
    @FXML private TextField T112;
    @FXML private TextField T113;
    @FXML private TextField T114;
    @FXML private TextField T115;
    @FXML private TextField T116;
    @FXML private TextField T117;
    @FXML private TextField T118;
    @FXML private TextField T119;
    @FXML private TextField T120;
    @FXML private TextField T121;
    @FXML private TextField T122;
    @FXML private TextField T123;
    @FXML private TextField T124;
    @FXML private TextField T125;
    @FXML private TextField T126;
    @FXML private TextField T127;
    @FXML private TextField T128;
    @FXML private TextField T129;
    @FXML private TextField T130;
    @FXML private TextField T131;
    @FXML private TextField T132;
    @FXML private TextField T133;
    @FXML private TextField T134;
    @FXML private TextField T135;
    @FXML private TextField T136;
    @FXML private TextField T137;
    @FXML private TextField T138;
    @FXML private TextField T139;
    @FXML private TextField T140;
    @FXML private TextField T141;
    @FXML private TextField T142;
    @FXML private TextField T143;
    @FXML private TextField T144;
    @FXML private TextField T145;
    @FXML private TextField T146;
    @FXML private TextField T147;
    @FXML private TextField T148;
    @FXML private TextField T149;
    @FXML private TextField T150;
    @FXML private TextField T151;
    @FXML private TextField T152;
    @FXML private TextField T153;
    @FXML private TextField T154;
    @FXML private TextField T155;
    @FXML private TextField T156;
    @FXML private TextField T157;
    @FXML private TextField T158;
    @FXML private TextField T159;
    @FXML private TextField T160;
    @FXML private TextField T161;
    @FXML private TextField T162;
    @FXML private TextField T163;
    @FXML private TextField T164;
    @FXML private TextField T165;
    @FXML private TextField T166;
    @FXML private TextField T167;
    @FXML private TextField T168;
    @FXML private TextField T169;
    @FXML private TextField T170;
    @FXML private TextField T171;
    @FXML private TextField T172;
    @FXML private TextField T173;
    @FXML private TextField T174;
    @FXML private TextField T175;
    @FXML private TextField T176;
    @FXML private TextField T177;
    @FXML private TextField T178;
    @FXML private TextField T179;
    @FXML private TextField T180;
    @FXML private TextField T181;
    @FXML private TextField T182;
    @FXML private TextField T183;
    @FXML private TextField T184;
    @FXML private TextField T185;
    @FXML private TextField T186;
    @FXML private TextField T187;
    @FXML private TextField T188;
    @FXML private TextField T189;
    @FXML private TextField T190;
    @FXML private TextField T191;
    @FXML private TextField T192;
    @FXML private TextField T193;
    @FXML private TextField T194;
    @FXML private TextField T195;
    @FXML private TextField T196;
    @FXML private TextField T197;
    @FXML private TextField T198;
    @FXML private TextField T199;
    @FXML private TextField T200;
    @FXML private TextField T201;
    @FXML private TextField T202;
    @FXML private TextField T203;
    @FXML private TextField T204;
    @FXML private TextField T205;
    @FXML private TextField T206;
    @FXML private TextField T207;
    @FXML private TextField T208;
    @FXML private TextField T209;
    @FXML private TextField T210;
    @FXML private TextField T211;
    @FXML private TextField T212;
    @FXML private TextField T213;
    @FXML private TextField T214;
    @FXML private TextField T215;
    @FXML private TextField T216;
    @FXML private TextField T217;
    @FXML private TextField T218;
    @FXML private TextField T219;
    @FXML private TextField T220;
    @FXML private TextField T221;
    @FXML private TextField T222;
    @FXML private TextField T223;
    @FXML private TextField T224;
    @FXML private TextField T225;
    @FXML private TextField T226;
    @FXML private TextField T227;
    @FXML private TextField T228;
    @FXML private TextField T229;
    @FXML private TextField T230;
    @FXML private TextField T231;
    @FXML private TextField T232;
    @FXML private TextField T233;
    @FXML private TextField T234;
    @FXML private TextField T235;
    @FXML private TextField T236;
    @FXML private TextField T237;
    @FXML private TextField T238;
    @FXML private TextField T239;
    @FXML private TextField T240;
    @FXML private TextField T241;
    @FXML private TextField T242;
    @FXML private TextField T243;
    @FXML private TextField T244;
    @FXML private TextField T245;
    @FXML private TextField T246;
    @FXML private TextField T247;
    @FXML private TextField T248;
    @FXML private TextField T249;
    @FXML private TextField T250;
    @FXML private TextField T251;
    @FXML private TextField T252;
    @FXML private TextField T253;
    @FXML private TextField T254;
    @FXML private TextField T255;
    @FXML private TextField T256;
    @FXML private TextField T257;
    @FXML private TextField T258;
    @FXML private TextField T259;
    @FXML private TextField T260;
    @FXML private TextField T261;
    @FXML private TextField T262;
    @FXML private TextField T263;
    @FXML private TextField T264;
    @FXML private TextField T265;
    @FXML private TextField T266;
    @FXML private TextField T267;
    @FXML private TextField T268;
    @FXML private TextField T269;
    @FXML private TextField T270;
    @FXML private TextField T271;
    @FXML private TextField T272;
    @FXML private TextField T273;
    @FXML private TextField T274;
    @FXML private TextField T275;
    @FXML private TextField T276;
    @FXML private TextField T277;
    @FXML private TextField T278;
    @FXML private TextField T279;
    @FXML private TextField T280;
    @FXML private TextField T281;
    @FXML private TextField T282;
    @FXML private TextField T283;
    @FXML private TextField T284;
    @FXML private TextField T285;
    @FXML private TextField T286;
    @FXML private TextField T287;
    @FXML private TextField T288;
    @FXML private TextField T289;
    @FXML private TextField T290;
    @FXML private TextField T291;
    @FXML private TextField T292;
    @FXML private TextField T293;
    @FXML private TextField T294;
    @FXML private TextField T295;
    @FXML private TextField T296;
    @FXML private TextField T297;
    @FXML private TextField T298;
    @FXML private TextField T299;
    @FXML private TextField T300;
    @FXML private TextField T301;
    @FXML private TextField T302;
    @FXML private TextField T303;
    @FXML private TextField T304;
    @FXML private TextField T305;
    @FXML private TextField T306;
    @FXML private TextField T307;
    @FXML private TextField T308;
    @FXML private TextField T309;
    @FXML private TextField T310;
    @FXML private TextField T311;
    @FXML private TextField T312;
    @FXML private TextField T313;
    @FXML private TextField T314;
    @FXML private TextField T315;
    @FXML private TextField T316;
    @FXML private TextField T317;
    @FXML private TextField T318;
    @FXML private TextField T319;
    @FXML private TextField T320;
    @FXML private TextField T321;
    @FXML private TextField T322;
    @FXML private TextField T323;
    @FXML private TextField T324;
    @FXML private TextField T325;
    @FXML private TextField T326;
    @FXML private TextField T327;
    @FXML private TextField T328;
    @FXML private TextField T329;
    @FXML private TextField T330;
    @FXML private TextField T331;
    @FXML private TextField T332;
    @FXML private TextField T333;
    @FXML private TextField T334;
    @FXML private TextField T335;
    @FXML private TextField T336;
    @FXML private TextField T337;
    @FXML private TextField T338;
    @FXML private TextField T339;
    @FXML private TextField T340;
    @FXML private TextField T341;
    @FXML private TextField T342;
    @FXML private TextField T343;
    @FXML private TextField T344;
    @FXML private TextField T345;
    @FXML private TextField T346;
    @FXML private TextField T347;
    @FXML private TextField T348;
    @FXML private TextField T349;
    @FXML private TextField T350;
    @FXML private TextField T351;
    @FXML private TextField T352;
    @FXML private TextField T353;
    @FXML private TextField T354;
    @FXML private TextField T355;
    @FXML private TextField T356;
    @FXML private TextField T357;
    @FXML private TextField T358;
    @FXML private TextField T359;
    @FXML private TextField T360;
    @FXML private TextField T361;
    @FXML private TextField T362;
    @FXML private TextField T363;
    @FXML private TextField T364;
    @FXML private TextField T365;
    @FXML private TextField T366;
    @FXML private TextField T367;
    @FXML private TextField T368;
    @FXML private TextField T369;
    @FXML private TextField T370;
    @FXML private TextField T371;
    @FXML private TextField T372;
    @FXML private TextField T373;
    @FXML private TextField T374;
    @FXML private TextField T375;
    @FXML private TextField T376;
    @FXML private TextField T377;
    @FXML private TextField T378;
    @FXML private TextField T379;
    @FXML private TextField T380;
    @FXML private TextField T381;
    @FXML private TextField T382;
    @FXML private TextField T383;
    @FXML private TextField T384;
    @FXML private TextField T385;
    @FXML private TextField T386;
    @FXML private TextField T387;
    @FXML private TextField T388;
    @FXML private TextField T389;
    @FXML private TextField T390;
    @FXML private TextField T391;
    @FXML private TextField T392;
    @FXML private TextField T393;
    @FXML private TextField T394;
    @FXML private TextField T395;
    @FXML private TextField T396;
    @FXML private TextField T397;
    @FXML private TextField T398;
    @FXML private TextField T399;
    @FXML private Label label;
    //</editor-fold>
    private List<TextField> textFields = new ArrayList<>();
    private List<Button> letterButtons = new ArrayList<>();
    private static TableController instance;
    private static Stage readyStage;
    private static Stage timerStage;
    private static String chosenLetter;
    private int index;
    private int letterIndex;
    private String[] oldboard = new String[400];


    public TableController(){
        instance = this;
    }

    public static TableController getInstance() {
        return instance;
    }

    public Stage getReadyStage(){
        return readyStage;
    }

    public Stage getTimerStage() {
        return timerStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //<editor-fold defaultstate="collapsed" desc="//Drag and Drop animation">
        borderPane.setOnMousePressed(event -> {
            xOffset = HallController.getStage().getX() - event.getScreenX();
            yOffset = HallController.getStage().getY() - event.getScreenY();
            borderPane.setCursor(Cursor.CLOSED_HAND);
        });

        borderPane.setOnMouseDragged(event -> {
            HallController.getStage().setX(event.getScreenX() + xOffset);
            HallController.getStage().setY(event.getScreenY() + yOffset);

        });

        borderPane.setOnMouseReleased(event -> {
            borderPane.setCursor(Cursor.DEFAULT);
        });
        //</editor-fold>
        stageZero();
        label.setVisible(false);
        resetPlayerStatus();
        for (int i = 0; i<400; i ++){ board[i]=""; }
        setTextFields();
        setBoard(board);
        setLetterButtons();

        for (int i = 0; i < 26 ; i++) {
            int finalI = i;
            // When Letters Clicked
            letterButtons.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
                chosenLetter=letterButtons.get(finalI).getText();
                letterIndex = finalI;
                letterButtons.get(finalI).setStyle("-fx-background-color: rgb(252,191,173);-fx-vgap: 5px;-fx-border-radius: 3px;-fx-border-color: gray;-fx-border-width: 2px;");
                for (int j = 0; j <26 ; j++) {
                    if (j != finalI){
                        letterButtons.get(j).setStyle("-fx-background-color: rgb(196,218,212);-fx-hgap:5px;-fx-vgap: 5px;-fx-border-radius: 3px;-fx-border-color: gray;-fx-border-width: 4px;");
                    }
                }
            });
        }
        for (int i = 0; i < 400; i++) {
            int finalI = i;
            //When Board Clicked
            textFields.get(i).addEventHandler(MouseEvent.MOUSE_PRESSED,(MouseEvent e)->{
                if (chosenLetter!=null){
                    if ((!board[finalI].isEmpty()) &&(Game.turn)){
                        Platform.runLater(()->{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Input Error!");
                            alert.setContentText("This grid has been occupied!");
                            alert.showAndWait();
                        });
                    }
                    else{
                        textFields.get(finalI).setText(chosenLetter);
                        chosenLetter=null;
                        letterButtons.get(letterIndex).setStyle("-fx-background-color: rgb(196,218,212);-fx-hgap:5px;-fx-vgap: 5px;-fx-border-radius: 3px;-fx-border-color: gray;-fx-border-width: 4px;");
                        stageTwo();
                        //TODO - select word
                    }
                }
            });
        }
    }

    private void setEditable(int location){
        if (textFields.get(location).getStyleClass().contains("Voting")) {
            textFields.get(location).getStyleClass().remove("Voting");
        }
        textFields.get(location).getStyleClass().add("NotEditable");
    }
    public void setCharacter(int setCharacter){
        Platform.runLater(()->{
            for (int i = 0; i<400; i++){
                if (textFields.get(i).getStyleClass().contains("Border")){
                    textFields.get(i).getStyleClass().remove("Border");
                }
            }
            if (setCharacter != -1)
            {textFields.get(setCharacter).getStyleClass().add("Border");}
        });
    }
    public void setBoard(String[] board){
        Platform.runLater(()->{
            for (int i =0;i<400;i++){
                textFields.get(i).setText(board[i]);
                if (!board[i].equals("")){
                    setEditable(i);
                }
            }
            this.board = board;
            this.oldboard = board;
        });
    }
    public String[] getOldBoard(){
        return oldboard;
    }

    public String[] getBoard(){
        String[] newBoard = new String [400];
        for (int i = 0; i<400; i++){
            newBoard[i] = textFields.get(i).getText();
        }
        return newBoard;
    }

    private boolean compare(){
        String[] newBoard = this.getBoard();
        int number = 0;
        for (int i =0;i<400;i++){
            if (!newBoard[i].equals(board[i])) {
                number++;
                index = i;
            }
        }
        if (number != 1){
            setBoard(board);
            Platform.runLater(()->{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Input Error!");
                alert.setContentText("You can only enter one letter during your turn.");
                alert.showAndWait();
            });
            return false;
        }
        else{
            board = getBoard();
            return true;
        }
    }

    // Reset Players' Information including Name, Score and Ready Status
    public void resetPlayerStatus(){
        Platform.runLater(()->{
            player1Name.setText("Empty");
            player2Name.setText("Empty");
            player3Name.setText("Empty");
            player4Name.setText("Empty");
            player1Score.setText("0");
            player2Score.setText("0");
            player3Score.setText("0");
            player4Score.setText("0");
            player1Ready.setVisible(false);
            player2Ready.setVisible(false);
            player3Ready.setVisible(false);
            player4Ready.setVisible(false);
        });
    }

    // Update Players' Turn
    public void refreshPlayerTurn(String name,Boolean turn){
        Platform.runLater(()->{
            if (player1Name.getText().equals(name)){
                if (turn){
                    player1Turn.setImage(new Image(getClass().getClassLoader().getResource("images/true.png").toString()));
                }else {
                    player1Turn.setImage(new Image(getClass().getClassLoader().getResource("images/false.png").toString()));
                }
            } else if (player2Name.getText().equals(name)){
                if (turn){
                    player2Turn.setImage(new Image(getClass().getClassLoader().getResource("images/true.png").toString()));
                }else {
                    player2Turn.setImage(new Image(getClass().getClassLoader().getResource("images/false.png").toString()));
                }
            } else if (player3Name.getText().equals(name)){
                if (turn){
                    player3Turn.setImage(new Image(getClass().getClassLoader().getResource("images/true.png").toString()));
                }else {
                    player3Turn.setImage(new Image(getClass().getClassLoader().getResource("images/false.png").toString()));
                }
            } else if (player4Name.getText().equals(name)){
                if (turn){
                    player4Turn.setImage(new Image(getClass().getClassLoader().getResource("images/true.png").toString()));
                }else {
                    player4Turn.setImage(new Image(getClass().getClassLoader().getResource("images/false.png").toString()));
                }
            }
        });
    }

    // All Players Ready -> set Ready status to invisible
    private void setAllReady(){
        Platform.runLater(()->{
            player1Ready.setVisible(false);
            player2Ready.setVisible(false);
            player3Ready.setVisible(false);
            player4Ready.setVisible(false);
        });
    }

    // Update Player's Score
    public void refreshPlayerScore(String name,String score){
        Platform.runLater(()->{
            if (player1Name.getText().equals(name)){
                player1Score.setText(score);
            } else if (player2Name.getText().equals(name)){
                player2Score.setText(score);
            } else if (player3Name.getText().equals(name)){
                player3Score.setText(score);
            } else if (player4Name.getText().equals(name)){
                player4Score.setText(score);
            }
        });
    }

    // Refresh Player's ready or not Status
    public void refreshPlayerStatus(String name,String status){
        Platform.runLater(()->{
            if (player1Name.getText().equals("Empty")){
                player1Name.setText(name);
                if (status.equals("NotReady")){
                    player1Ready.setVisible(false);
                }else if (status.equals("Ready")){
                    player1Ready.setVisible(true);
                }
            }else if (player2Name.getText().equals("Empty")){
                player2Name.setText(name);
                if (status.equals("NotReady")){
                    player2Ready.setVisible(false);
                }else if (status.equals("Ready")){
                    player2Ready.setVisible(true);
                }
            }else if (player3Name.getText().equals("Empty")){
                player3Name.setText(name);
                if (status.equals("NotReady")){
                    player3Ready.setVisible(false);
                }else if (status.equals("Ready")){
                    player3Ready.setVisible(true);
                }
            }else if (player4Name.getText().equals("Empty")){
                player4Name.setText(name);
                if (status.equals("NotReady")){
                    player4Ready.setVisible(false);
                }else if (status.equals("Ready")){
                    player4Ready.setVisible(true);
                }
            }
        });
    }

    // Voting Confirmation
    public void colorWord(boolean direction, int index){
        Platform.runLater(()->{
            if (direction){
                Game.horizontal(index,board);
            }
            else{
                Game.vertical(index,board);
            }
            for (int i=0; i<Game.wordLocation.size();i++){
                textFields.get(Game.wordLocation.get(i)).getStyleClass().add("Voting");
            }
        });
    }
    public void colorRecover(boolean direction, int index){
        for (int i=0; i<Game.wordLocation.size();i++){
            textFields.get(Game.wordLocation.get(i)).getStyleClass().remove("Voting");
            textFields.get(Game.wordLocation.get(i)).getStyleClass().add("NotEditable");
        }
    }
    public void voting(String name,String word,boolean direction,int index){
        Platform.runLater(()->{
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Voting Confirmation");
            alert1.setHeaderText("Voting ");
            alert1.setContentText("Do you really think < "+word+" > is a word ?");
            colorWord(direction,index);
            ButtonType buttonyes = new ButtonType("Yes");
            ButtonType buttonno = new ButtonType("No");
            alert1.getButtonTypes().setAll(buttonyes,buttonno);
            Optional<ButtonType> result1 = alert1.showAndWait();
            if(result1.get()==buttonyes) {
                Game.voting(true,name,word);
                //colorRecover(direction,index);
            }
            else if(result1.get()==buttonno) {
                Game.voting(false,name,word);
                //colorRecover(direction,index);
            }
        });
    }

    // Show 3 2 1 count down
    public void gameStart() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/timer.fxml"));
        Parent window = (Pane) fxmlLoader.load();
        TimerController timerController = fxmlLoader.getController();
        Scene timerScene = new Scene(window);
        timerScene.setFill(null);
        Platform.runLater(()->{
            timerStage = new Stage();
            timerStage.initOwner(title.getScene().getWindow());
            timerStage.initStyle(StageStyle.UNDECORATED);
            timerStage.initStyle(StageStyle.TRANSPARENT);
            timerStage.initModality(Modality.APPLICATION_MODAL);
            timerStage.setWidth(TableWidth);
            timerStage.setHeight(TableHeight);
            timerStage.setX(HallController.getStage().getX());
            timerStage.setY(HallController.getStage().getY());
            timerStage.setScene(timerScene);
            timerStage.show();
        });
        this.setAllReady();
    }

    // Initialize textFields
    private void setTextFields(){
        textFields.add(T0);textFields.add(T1);textFields.add(T2);textFields.add(T3);textFields.add(T4);
        textFields.add(T5);textFields.add(T6);textFields.add(T7);textFields.add(T8);textFields.add(T9);
        textFields.add(T10);textFields.add(T11);textFields.add(T12);textFields.add(T13);textFields.add(T14);
        textFields.add(T15);textFields.add(T16);textFields.add(T17);textFields.add(T18);textFields.add(T19);
        textFields.add(T20);textFields.add(T21);textFields.add(T22);textFields.add(T23);textFields.add(T24);
        textFields.add(T25);textFields.add(T26);textFields.add(T27);textFields.add(T28);textFields.add(T29);
        textFields.add(T30);textFields.add(T31);textFields.add(T32);textFields.add(T33);textFields.add(T34);
        textFields.add(T35);textFields.add(T36);textFields.add(T37);textFields.add(T38);textFields.add(T39);
        textFields.add(T40);textFields.add(T41);textFields.add(T42);textFields.add(T43);textFields.add(T44);
        textFields.add(T45);textFields.add(T46);textFields.add(T47);textFields.add(T48);textFields.add(T49);
        textFields.add(T50);textFields.add(T51);textFields.add(T52);textFields.add(T53);textFields.add(T54);
        textFields.add(T55);textFields.add(T56);textFields.add(T57);textFields.add(T58);textFields.add(T59);
        textFields.add(T60);textFields.add(T61);textFields.add(T62);textFields.add(T63);textFields.add(T64);
        textFields.add(T65);textFields.add(T66);textFields.add(T67);textFields.add(T68);textFields.add(T69);
        textFields.add(T70);textFields.add(T71);textFields.add(T72);textFields.add(T73);textFields.add(T74);
        textFields.add(T75);textFields.add(T76);textFields.add(T77);textFields.add(T78);textFields.add(T79);
        textFields.add(T80);textFields.add(T81);textFields.add(T82);textFields.add(T83);textFields.add(T84);
        textFields.add(T85);textFields.add(T86);textFields.add(T87);textFields.add(T88);textFields.add(T89);
        textFields.add(T90);textFields.add(T91);textFields.add(T92);textFields.add(T93);textFields.add(T94);
        textFields.add(T95);textFields.add(T96);textFields.add(T97);textFields.add(T98);textFields.add(T99);
        textFields.add(T100);textFields.add(T101);textFields.add(T102);textFields.add(T103);textFields.add(T104);
        textFields.add(T105);textFields.add(T106);textFields.add(T107);textFields.add(T108);textFields.add(T109);
        textFields.add(T110);textFields.add(T111);textFields.add(T112);textFields.add(T113);textFields.add(T114);
        textFields.add(T115);textFields.add(T116);textFields.add(T117);textFields.add(T118);textFields.add(T119);
        textFields.add(T120);textFields.add(T121);textFields.add(T122);textFields.add(T123);textFields.add(T124);
        textFields.add(T125);textFields.add(T126);textFields.add(T127);textFields.add(T128);textFields.add(T129);
        textFields.add(T130);textFields.add(T131);textFields.add(T132);textFields.add(T133);textFields.add(T134);
        textFields.add(T135);textFields.add(T136);textFields.add(T137);textFields.add(T138);textFields.add(T139);
        textFields.add(T140);textFields.add(T141);textFields.add(T142);textFields.add(T143);textFields.add(T144);
        textFields.add(T145);textFields.add(T146);textFields.add(T147);textFields.add(T148);textFields.add(T149);
        textFields.add(T150);textFields.add(T151);textFields.add(T152);textFields.add(T153);textFields.add(T154);
        textFields.add(T155);textFields.add(T156);textFields.add(T157);textFields.add(T158);textFields.add(T159);
        textFields.add(T160);textFields.add(T161);textFields.add(T162);textFields.add(T163);textFields.add(T164);
        textFields.add(T165);textFields.add(T166);textFields.add(T167);textFields.add(T168);textFields.add(T169);
        textFields.add(T170);textFields.add(T171);textFields.add(T172);textFields.add(T173);textFields.add(T174);
        textFields.add(T175);textFields.add(T176);textFields.add(T177);textFields.add(T178);textFields.add(T179);
        textFields.add(T180);textFields.add(T181);textFields.add(T182);textFields.add(T183);textFields.add(T184);
        textFields.add(T185);textFields.add(T186);textFields.add(T187);textFields.add(T188);textFields.add(T189);
        textFields.add(T190);textFields.add(T191);textFields.add(T192);textFields.add(T193);textFields.add(T194);
        textFields.add(T195);textFields.add(T196);textFields.add(T197);textFields.add(T198);textFields.add(T199);
        textFields.add(T200);textFields.add(T201);textFields.add(T202);textFields.add(T203);textFields.add(T204);
        textFields.add(T205);textFields.add(T206);textFields.add(T207);textFields.add(T208);textFields.add(T209);
        textFields.add(T210);textFields.add(T211);textFields.add(T212);textFields.add(T213);textFields.add(T214);
        textFields.add(T215);textFields.add(T216);textFields.add(T217);textFields.add(T218);textFields.add(T219);
        textFields.add(T220);textFields.add(T221);textFields.add(T222);textFields.add(T223);textFields.add(T224);
        textFields.add(T225);textFields.add(T226);textFields.add(T227);textFields.add(T228);textFields.add(T229);
        textFields.add(T230);textFields.add(T231);textFields.add(T232);textFields.add(T233);textFields.add(T234);
        textFields.add(T235);textFields.add(T236);textFields.add(T237);textFields.add(T238);textFields.add(T239);
        textFields.add(T240);textFields.add(T241);textFields.add(T242);textFields.add(T243);textFields.add(T244);
        textFields.add(T245);textFields.add(T246);textFields.add(T247);textFields.add(T248);textFields.add(T249);
        textFields.add(T250);textFields.add(T251);textFields.add(T252);textFields.add(T253);textFields.add(T254);
        textFields.add(T255);textFields.add(T256);textFields.add(T257);textFields.add(T258);textFields.add(T259);
        textFields.add(T260);textFields.add(T261);textFields.add(T262);textFields.add(T263);textFields.add(T264);
        textFields.add(T265);textFields.add(T266);textFields.add(T267);textFields.add(T268);textFields.add(T269);
        textFields.add(T270);textFields.add(T271);textFields.add(T272);textFields.add(T273);textFields.add(T274);
        textFields.add(T275);textFields.add(T276);textFields.add(T277);textFields.add(T278);textFields.add(T279);
        textFields.add(T280);textFields.add(T281);textFields.add(T282);textFields.add(T283);textFields.add(T284);
        textFields.add(T285);textFields.add(T286);textFields.add(T287);textFields.add(T288);textFields.add(T289);
        textFields.add(T290);textFields.add(T291);textFields.add(T292);textFields.add(T293);textFields.add(T294);
        textFields.add(T295);textFields.add(T296);textFields.add(T297);textFields.add(T298);textFields.add(T299);
        textFields.add(T300);textFields.add(T301);textFields.add(T302);textFields.add(T303);textFields.add(T304);
        textFields.add(T305);textFields.add(T306);textFields.add(T307);textFields.add(T308);textFields.add(T309);
        textFields.add(T310);textFields.add(T311);textFields.add(T312);textFields.add(T313);textFields.add(T314);
        textFields.add(T315);textFields.add(T316);textFields.add(T317);textFields.add(T318);textFields.add(T319);
        textFields.add(T320);textFields.add(T321);textFields.add(T322);textFields.add(T323);textFields.add(T324);
        textFields.add(T325);textFields.add(T326);textFields.add(T327);textFields.add(T328);textFields.add(T329);
        textFields.add(T330);textFields.add(T331);textFields.add(T332);textFields.add(T333);textFields.add(T334);
        textFields.add(T335);textFields.add(T336);textFields.add(T337);textFields.add(T338);textFields.add(T339);
        textFields.add(T340);textFields.add(T341);textFields.add(T342);textFields.add(T343);textFields.add(T344);
        textFields.add(T345);textFields.add(T346);textFields.add(T347);textFields.add(T348);textFields.add(T349);
        textFields.add(T350);textFields.add(T351);textFields.add(T352);textFields.add(T353);textFields.add(T354);
        textFields.add(T355);textFields.add(T356);textFields.add(T357);textFields.add(T358);textFields.add(T359);
        textFields.add(T360);textFields.add(T361);textFields.add(T362);textFields.add(T363);textFields.add(T364);
        textFields.add(T365);textFields.add(T366);textFields.add(T367);textFields.add(T368);textFields.add(T369);
        textFields.add(T370);textFields.add(T371);textFields.add(T372);textFields.add(T373);textFields.add(T374);
        textFields.add(T375);textFields.add(T376);textFields.add(T377);textFields.add(T378);textFields.add(T379);
        textFields.add(T380);textFields.add(T381);textFields.add(T382);textFields.add(T383);textFields.add(T384);
        textFields.add(T385);textFields.add(T386);textFields.add(T387);textFields.add(T388);textFields.add(T389);
        textFields.add(T390);textFields.add(T391);textFields.add(T392);textFields.add(T393);textFields.add(T394);
        textFields.add(T395);textFields.add(T396);textFields.add(T397);textFields.add(T398);textFields.add(T399);
    }

    // Initialize letterButtons
    private void setLetterButtons(){
        letterButtons.add(A);
        letterButtons.add(B);
        letterButtons.add(C);
        letterButtons.add(D);
        letterButtons.add(E);
        letterButtons.add(F);
        letterButtons.add(G);
        letterButtons.add(H);
        letterButtons.add(I);
        letterButtons.add(J);
        letterButtons.add(K);
        letterButtons.add(L);
        letterButtons.add(M);
        letterButtons.add(N);
        letterButtons.add(O);
        letterButtons.add(P);
        letterButtons.add(Q);
        letterButtons.add(R);
        letterButtons.add(S);
        letterButtons.add(T);
        letterButtons.add(U);
        letterButtons.add(V);
        letterButtons.add(W);
        letterButtons.add(X);
        letterButtons.add(Y);
        letterButtons.add(Z);
        for (int i = 0; i <26 ; i++) {
            letterButtons.get(i).setStyle("-fx-background-color: rgb(196,218,212);-fx-hgap:5px;-fx-vgap: 5px;-fx-border-radius: 3px;-fx-border-color: gray;-fx-border-width: 4px;");
        }
    }

    // Stage 1 - put letter on the board
    public void stageZero(){
        tileBox.setVisible(false);
        confirmBtn.setVisible(false);
        clearBtn.setVisible(false);
        passBtn.setVisible(false);
        label.setVisible(true);
    }
    public void stageOne(){
        tileBox.setVisible(true);
        confirmBtn.setVisible(false);
        clearBtn.setVisible(false);
        passBtn.setVisible(true);
        label.setVisible(false);
    }

    // Stage 2 - Clear or Select word

    private void stageTwo(){
        tileBox.setVisible(false);
        passBtn.setVisible(true);
        confirmBtn.setVisible(true);
        clearBtn.setVisible(true);
        label.setVisible(false);
    }

    // Show Ready Scene
    @FXML public void showReadyStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/ready.fxml"));
        Parent window = (Pane) fxmlLoader.load();
        ReadyController readyController = fxmlLoader.getController();
        readyController.title.setText(HallController.tableNumber);
        Scene scene = new Scene(window);
        scene.setFill(null);

        Platform.runLater(() -> {
            readyStage = new Stage();
            // Paternity
            readyStage.initOwner(title.getScene().getWindow());
            readyStage.initStyle(StageStyle.UNDECORATED);
            readyStage.initStyle(StageStyle.TRANSPARENT);
            readyStage.initModality(Modality.APPLICATION_MODAL);
            readyStage.setWidth(TableWidth);
            readyStage.setHeight(TableHeight);
            readyStage.setScene(scene);
            readyStage.show();
        });
    }

    // Confirm Button Action
    @FXML private void confirm() {
        if (Game.turn){
            Platform.runLater(()->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Word Confirmation");
                alert.setHeaderText("Please choose a direction:");
                alert.setContentText("Choose your option.");
                ButtonType buttonTypeH = new ButtonType("Horizontal");
                ButtonType buttonTypeV = new ButtonType("Vertical");
                ButtonType buttonTypeS = new ButtonType("Single letter");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonTypeH, buttonTypeV, buttonTypeS, buttonTypeCancel);
                Optional<ButtonType> result = alert.showAndWait();
                String word =null;
                String inputRegex = "^[a-zA-Z]{1}$";
                // user chose "Horizontal"
                if (result.get() == buttonTypeH){
                    if (compare() == true){
                        if (!getBoard()[index].matches(inputRegex)){
                            Platform.runLater(()->{
                                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                alert1.setHeaderText("Input Error!");
                                alert1.setContentText("You can only enter one letter in the box.");
                                alert1.showAndWait();
                            });
                        } else {
                            word = Game.horizontal(index,getBoard());
                            textFields.get(index).getStyleClass().add("Border");
                            Game.sendWord(index,getBoard()[index].toUpperCase(),Game.wordLocation,word,true);
                        }
                    }
                    // user chose "Vertical"
                } else if (result.get() == buttonTypeV) {
                    if (compare() == true){
                        word = Game.vertical(index,getBoard());
                        textFields.get(index).getStyleClass().add("Border");
                        Game.sendWord(index,getBoard()[index].toUpperCase(),Game.wordLocation,word,false);
                    }
                    // user chose "Single letter"
                } else if (result.get() == buttonTypeS) {
                    if (compare() == true) {
                        Game.sendCharacter(index, board[index]);
                    }
                }
            });
        } else {
            Platform.runLater(()->{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Not your turn!");
                alert.setContentText("Please wait for others...");
                alert.showAndWait();
            });
        }
    }

    // Clear Button Action
    @FXML private void clearBtnAction(){
        stageOne();
        setBoard(board);
    }

    // Pass Button Action
    @FXML private void pass(){
        if (Game.turn){
            Game.pass();
            stageZero();
        }else {
            Platform.runLater(()->{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Not your turn!");
                alert.setContentText("Please wait for others...");
                alert.showAndWait();
            });
        }
    }

    // Help Button Action
    @FXML private void help(){
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Welcome to Scrabble");
            alert.setHeaderText("About Scrabble:");
            alert.setContentText(" ·The players turn by turn can place alphabet tiles on the grid to form a word.\r\n\r\n· The player who places the associated letter"
                    + " will get points equal to the length of the word.\r\n\r\n· The word is considered valid if all the other players vote in favour of the word."
                    + "\r\n\r\n ·The game ends when there is no place in the grid.");
            alert.showAndWait();
        });
    }

    // return to game hall
    @FXML private void returnHall() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure you want to exit this game ?");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            HallController.getStage().close();
            Game.getPrimaryStage().show();
            Game.returnToHall();
            Game.inGame = false;
        }
    }

    // Minimize Window
    @FXML private void minimizeWindow(){
        HallController.getStage().setIconified(true);
    }

}
