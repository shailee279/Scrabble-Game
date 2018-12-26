package com.server;

import com.game.GameRoom;
import com.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EachConnection implements Runnable {

    private int score;
    private Socket clientSocket;
    private static Server server;
    private PlayerStatus clientStatus;
    private PlayerAction clientAction;
    private int clientNum;
    private InputStream in;
    private OutputStream out;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String clientName;
    private int tableId;
    static Logger logger = LoggerFactory.getLogger(EachConnection.class);


    public EachConnection(Socket clientSocket, int clientNum) throws IOException{
        this.clientSocket = clientSocket;
        this.clientNum = clientNum;
        clientAction = PlayerAction.CONNECT;
        out = clientSocket.getOutputStream();
        oos = new ObjectOutputStream(out);
        clientAction = PlayerAction.CONNECT;
        in = clientSocket.getInputStream();
        ois = new ObjectInputStream(in);
    }


    // listening from client messages
    @Override
    public void run() {
        try {
            while (true){
                Message clientMsg = (Message) ois.readObject();

                if (clientMsg != null){
                    switch (clientMsg.getPlayerStatus()){
                        case SET_NAME:
                            inSetName(clientMsg);
                            break;
                        case IN_HALL:
                            inHall(clientMsg);
                            break;
                        case IN_ROOM:
                            inRoom(clientMsg);
                            break;
                        case IN_GAME:
                            inGame(clientMsg);
                            break;
                    }
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            if (clientName != null) {
                ServerState.UserList.remove(clientName);
                ServerState.clientList.remove(clientName);
            }
            if ((clientStatus == PlayerStatus.IN_GAME) || (clientStatus == PlayerStatus.IN_ROOM)) {
                GameRoom game = getCurrentGame();
                if (game.isGameStart()) {
                    if (game.getNumOfPlayer() >= 2) {
                        updateGameList();
                        game.deletePlayer(clientNum, clientName);
                        hall_information();
                        game_information();
                        if (!game.isEnding()) {
                            gameResult(game.gameResult());
                        }
                    } else {
                        if (game.getNumOfPlayer() <= 1) {
                            game.deletePlayer(clientNum, clientName);
                            hall_information();
                            ServerState.getGameInstance().gameDisconnected(game);
                        }
                    }
                } else {
                    game.deletePlayer(clientNum, clientName);
                    updateGameList();
                    hall_information();
//                table_information();
                }
            }
            setClientStatus(PlayerStatus.LEAVING);
            ServerState.getClientInstance().clientDisconnected(this);
            updateGameList();
            hall_information();
            logger.info("Client on port " + clientSocket.getPort() + " exited.");
        }
    }

    private void hall_information() {
        Message toALL = new Message();
        updateGameList();
        List<EachConnection> clients = ServerState.getClientInstance().getConnectedClients();
        toALL.setPlayerStatus(PlayerStatus.IN_HALL);
        toALL.setPlayerAction(PlayerAction.HALL_WAITING);
        toALL.setConnectedClients(ServerState.clientList);
        toALL.setCreatedGames(ServerState.gameList);
        List<EachConnection> inHall = new ArrayList<>();
        for (EachConnection inhall : clients){
            if (inhall.getClientStatus() == PlayerStatus.IN_HALL){
                inHall.add(inhall);
            }
        }
        broadCast(inHall,toALL);
    }
    private void table_information() {
        Message toPlayers = new Message();
        GameRoom game = getCurrentGame();
        toPlayers.setPlayerStatus(PlayerStatus.IN_ROOM);
        toPlayers.setPlayerAction(PlayerAction.GAME_WAITING);
        EachConnection[] players = game.getPlayerList();
        toPlayers.setPlayerList(game.getPlayerStatus());
        roombroadCast(players,toPlayers);
    }

    private void game_information(){
        Message toPlayers = new Message();
        GameRoom game = getCurrentGame();
        toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
        toPlayers.setPlayerAction(PlayerAction.GAME_CONTENT);
        toPlayers.setPlayerScore(game.getPlayerScore());
        EachConnection[] players = game.getPlayerList();
        toPlayers.setBoard(game.getBoard());
        toPlayers.setPlayerList(game.getPlayerStatus());
        roombroadCast(players,toPlayers);
    }

    // in setName status
    private synchronized void inSetName (Message m) throws IOException{
        String name = m.getClientName();
        Message toClient = new Message();
        if (!ServerState.UserList.contains(name)){
            setClientStatus(PlayerStatus.IN_HALL);
            setClientName(name);
            ServerState.UserList.add(name);
            ServerState.clientList.put(clientName,"Online");
            toClient.setPlayerStatus(PlayerStatus.SET_NAME);
            updateGameList();
            toClient.setClientName(name);
            toClient.setFeedBackMessage("ValidName");
            oos.writeObject(toClient);
            updateGameList();
            hall_information();
        }else{
            toClient.setPlayerStatus(PlayerStatus.SET_NAME);
            toClient.setClientName(name);
            toClient.setFeedBackMessage("InvalidName");
            oos.writeObject(toClient);
        }
    }
    // in hall status
    private synchronized void inHall(Message m) throws IOException{
        if (m.getPlayerAction() == PlayerAction.JOIN_TABLE){
            tableId = m.getTableId();
            join(tableId);
        }
    }

    private void join(int tableId)throws IOException{
        Message toClient = new Message();
        List<GameRoom> gameRooms = ServerState.getGameInstance().getConnectedGames();

        switch (tableValid(tableId,gameRooms)){
            case "ValidTable":
                this.tableId = tableId;
                GameRoom game = getCurrentGame();
                game.addPlayer(this.clientNum);
                setClientStatus(PlayerStatus.IN_ROOM);
                ServerState.clientList.replace(clientName,"In Game");
                toClient.setPlayerStatus(PlayerStatus.IN_HALL);
                toClient.setPlayerAction(PlayerAction.JOIN_TABLE);
                toClient.setFeedBackMessage("ValidTable");
                oos.writeObject(toClient);
                hall_information();
                table_information();
                break;
            case "TableNotExist":
                GameRoom gameRoom = new GameRoom(this.clientNum,tableId);
                ServerState.getGameInstance().gameConnected(gameRoom);
                this.tableId = tableId;
                setClientStatus(PlayerStatus.IN_ROOM);
                ServerState.clientList.replace(clientName,"In Game");
                // set message
                toClient.setPlayerStatus(PlayerStatus.IN_HALL);
                toClient.setPlayerAction(PlayerAction.JOIN_TABLE);
                toClient.setFeedBackMessage("ValidTable");
                oos.writeObject(toClient);
                hall_information();
                table_information();
                break;
            case "InvalidTable":
                toClient.setPlayerStatus(PlayerStatus.IN_HALL);
                toClient.setPlayerAction(PlayerAction.JOIN_TABLE);
                toClient.setFeedBackMessage("InvalidTable");
                oos.writeObject(toClient);
                break;
        }
    }

    private String tableValid(int tableId,List<GameRoom> gameRooms){
        for (int i = 0; i < gameRooms.size(); i++) {
            if (gameRooms.get(i).getTableId() == tableId) {
                if (gameRooms.get(i).isGameStart()) {
                    return "InvalidTable";
                } else if (gameRooms.get(i).getNumOfPlayer() < gameRooms.get(i).getMaximumPlayerNumber()) {
                    return "ValidTable";
                } else {
                    return "InvalidTable";
                }
            }
        }
        return "TableNotExist";
    }

    private int tableIndex(int tableId,List<GameRoom> gameRooms) {
        int index = -1;
        for (int i = 0; i < gameRooms.size(); i++) {
            if (gameRooms.get(i).getTableId() == tableId) {
                index = i;
                return index;
            }
        }
        return index;
    }
    // in room status
    private synchronized void inRoom(Message m) throws IOException{
        if (m.getPlayerAction() == PlayerAction.READY){
            ready();
        }
        if (m.getPlayerAction() == PlayerAction.INVITE){
            Message toClient = new Message();
            toClient.setPlayerStatus(PlayerStatus.IN_ROOM);
            toClient.setPlayerAction(PlayerAction.INVITE);
            List<EachConnection> clients = ServerState.getClientInstance().getConnectedClients();
            List<EachConnection> inHall = new ArrayList<>();
            for (EachConnection inhall : clients){
                if (inhall.getClientStatus() == PlayerStatus.IN_HALL){
                    inHall.add(inhall);
                }
            }
            Map<String,String> inviteList = new HashMap<>();
            for(EachConnection player : inHall){
                inviteList.put(player.getClientName(),player.getClientName());
            }
            toClient.setPlayerList(inviteList);
            oos.writeObject(toClient);
        }

        if (m.getPlayerAction() == PlayerAction.INVITE_PLAYER){
            String name = m.getClientName();
            Message toClient = new Message();
            List<EachConnection> clients = ServerState.getClientInstance().getConnectedClients();
            boolean playerExist = false;
            for (EachConnection client : clients) {
                if (client.getClientName().equals(name)) {
                    if (client.getClientStatus().equals(PlayerStatus.IN_HALL)){
                        playerExist = true;
                        toClient.setPlayerStatus(PlayerStatus.IN_HALL);
                        toClient.setPlayerAction(PlayerAction.INVITE_PLAYER);
                        toClient.setClientName(this.clientName);
                        toClient.setTableId(this.tableId);
                        client.write(toClient);
                        Message toSender = new Message();
                        toSender.setPlayerStatus(PlayerStatus.IN_ROOM);
                        toSender.setPlayerAction(PlayerAction.INVITE_FEEDBACK);
                        toSender.setFeedBackMessage("The invitation to <" + name + "> has been send success");
                        List<EachConnection> inHall = new ArrayList<>();
                        for (EachConnection inhall : clients) {
                            if (inhall.getClientStatus() == PlayerStatus.IN_HALL) {
                                inHall.add(inhall);
                            }
                        }
                        Map<String, String> inviteList = new HashMap<>();
                        for (EachConnection player : inHall) {
                            inviteList.put(player.getClientName(), player.getClientName());
                        }
                        toSender.setPlayerList(inviteList);
                        oos.writeObject(toSender);
                    }
                }
            }
            if (playerExist == false) {
                toClient.setPlayerStatus(PlayerStatus.IN_ROOM);
                toClient.setPlayerAction(PlayerAction.INVITE_FEEDBACK);
                List<EachConnection> inHall = new ArrayList<>();
                for (EachConnection inhall : clients) {
                    if (inhall.getClientStatus() == PlayerStatus.IN_HALL) {
                        inHall.add(inhall);
                    }
                }
                Map<String, String> inviteList = new HashMap<>();
                for (EachConnection player : inHall) {
                    inviteList.put(player.getClientName(), player.getClientName());
                }
                toClient.setPlayerList(inviteList);
                toClient.setFeedBackMessage("<" + name + "> cannot be invited now.");
                oos.writeObject(toClient);
            }
        }


        if (m.getPlayerAction() == PlayerAction.INVITE_FEEDBACK){
            String name = m.getClientName();
            Message toClient = new Message();
            List<EachConnection> clients = ServerState.getClientInstance().getConnectedClients();
            toClient.setPlayerStatus(PlayerStatus.IN_ROOM);
            toClient.setPlayerAction(PlayerAction.INVITE_FEEDBACK);
            if (m.getFeedBackMessage().equals("R")){
                toClient.setFeedBackMessage("<"+this.clientName + "> rejected your invitation.");
            }
            if (m.getFeedBackMessage().equals("A")){
                toClient.setFeedBackMessage("<"+this.clientName + "> accepted your invitation.");
            }
            for (EachConnection client : clients) {
                if (client.getClientName().equals(name)) {
                    List<EachConnection> inHall = new ArrayList<>();
                    for (EachConnection inhall : clients){
                        if (inhall.getClientStatus() == PlayerStatus.IN_HALL && !(m.getFeedBackMessage().equals("A") && inhall.getClientName().equals(this.clientName))){
                            inHall.add(inhall);
                        }
                    }
                    Map<String,String> inviteList = new HashMap<>();
                    for(EachConnection player : inHall){
                        inviteList.put(player.getClientName(),player.getClientName());
                    }
                    toClient.setPlayerList(inviteList);
                    client.write(toClient);
                }
            }
        }


        if (m.getPlayerAction() == PlayerAction.RETURN_HALL) {
            setClientStatus(PlayerStatus.IN_HALL);
            setClientAction(PlayerAction.HALL_WAITING);
            ServerState.clientList.replace(clientName, "Online");
            GameRoom game = getCurrentGame();
            if (game.isGameStart()) {
                if (game.getNumOfPlayer() >= 2) {
                    game.deletePlayer(clientNum, clientName);
                    updateGameList();
                    hall_information();
                    game_information();
                    if (!game.isEnding()) {
                        gameResult(game.gameResult());
                    }
                } else {
                    if (game.getNumOfPlayer() <= 1) {
                        game.deletePlayer(clientNum, clientName);
                        updateGameList();
                        hall_information();
                        ServerState.getGameInstance().gameDisconnected(game);
                    }
                }
            } else {
                game.deletePlayer(clientNum, clientName);
                updateGameList();
                hall_information();
                //table_information();
            }
        }

        if (m.getGameStatus() == GameStatus.ALL_READY){
            setClientStatus(PlayerStatus.IN_GAME);
            setClientAction(PlayerAction.GAME_WAITING);
        }
    }

    private void ready() throws IOException{
        int numReady= 0;
        GameRoom game = getCurrentGame();
        EachConnection[] players = game.getPlayerList();
        game.playerReady(clientName);
        // logic part
        setClientAction(PlayerAction.READY);
        table_information();
        //check how many players are in ready condition
        for (int i = 0; i < game.getNumOfPlayer(); i++) {
            if (players[i].getClientAction().equals(PlayerAction.READY)){
                numReady +=1;
            }
        }
        if ( (numReady == game.getNumOfPlayer()) && (numReady >= GameRoom.getMinimumPlayerNumber()) ){
            //TODO game status - all ready
            Message toPlayers = new Message();
            game.setGameStart(true);
            toPlayers.setPlayerStatus(PlayerStatus.IN_ROOM);
            toPlayers.setGameStatus(GameStatus.ALL_READY);
            roombroadCast(players,toPlayers);
            game.initialGame();
            game_information();
        }
    }
    private synchronized void inGame(Message m){
        switch (m.getPlayerAction()){
            case SET_CHARACTER:
                singleCharacter(m);
                break;
            case SET_WORD:
                GameRoom game = getCurrentGame();
                game.setPassNum(0);
                game.setVotingNum(0);
                game.setVotingYes(0);
                gameContent(m);
                break;
            case VOTING:
                voting(m);
                break;
            case PASS:
                pass();
                break;
        }
    }
    private void singleCharacter(Message m){
        GameRoom game = getCurrentGame();
        game.addCharacter(m.getGameLocation(),m.getGameCharacter());
        game.SpaceRemain();
        game.turnPass(this.clientName);
        game_information();
    }


    private void gameContent(Message m){
        GameRoom game = getCurrentGame();
        EachConnection[] players = game.getPlayerList();
        Message toPlayers = new Message();
        //players bc
        game.addCharacter(m.getGameLocation(),m.getGameCharacter());
        game_information();
        //broadcast
        toPlayers.setGameWord(m.getGameWord());
        toPlayers.setClientName(this.clientName);
        toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
        toPlayers.setPlayerAction(PlayerAction.VOTING);
        toPlayers.setGameLocation(m.getGameLocation());
        toPlayers.setWordLocation(m.getWordLocation());
        toPlayers.setDirection(m.getDirection());
        roombroadCast(players,toPlayers);
    }

    private void voting(Message m){
        GameRoom game = getCurrentGame();
        String name = m.getClientName();
        EachConnection[] players = game.getPlayerList();
        Message toPlayers = new Message();
        toPlayers.setClientName(name);
        // voting +1
        game.voting(m.getVotingResult());
        switch (game.votingResult()){
            case "Accept":
                game.setPlayerScore(name,game.getScore(name)+m.getGameWord().length());
                game.SpaceRemain();
                toPlayers.setVotingResult(true);
                game.turnPass(name);
                if (!game.gameEnd()){
                    toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
                    toPlayers.setPlayerAction(PlayerAction.VOTING);
                    game_information();
                }else{
                    if(!game.isEnding()){
                        gameResult(game.gameResult());
                    }
                }
                break;
            case "Reject":
                toPlayers.setVotingResult(false);
                game.turnPass(name);
                game.SpaceRemain();

                if (!game.gameEnd()){
                    toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
                    toPlayers.setPlayerAction(PlayerAction.VOTING);
                    game_information();
                }else{
                    // return game result
                    if(!game.isEnding()){
                        gameResult(game.gameResult());
                    }
                }
                break;
            case "inProgress":
                break;
        }
    }

    //TODO pass logic is wrong
    private void pass(){
        GameRoom game = getCurrentGame();
        EachConnection[] players = game.getPlayerList();
        Message toPlayers = new Message();
        // # of pass +1
        game.pass();

        switch (game.passResult()){
            case "GameEnd":
                // return game result
                game.initialBoard();
                if (!game.isEnding()){
                    gameResult(game.gameResult());
                }
                break;
            case "GameContinue":
                //TODO - turn pass
                game.turnPass(this.clientName);
                game_information();
                break;
        }
    }

    private void roombroadCast(EachConnection[] players,Message m){
        GameRoom game = getCurrentGame();
        for (int i = 0; i < game.getNumOfPlayer(); i++) {
            players[i].write(m);
        }
    }

    private void broadCast(List<EachConnection> clients, Message m){
        for(EachConnection client : clients) {
            client.write(m);
        }
    }

    private synchronized void write(Message m) {
        try {
            oos.writeObject(m);
            oos.flush();
            oos.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getClientNum() {
        return clientNum;
    }

    public void setClientNum(int clientNum) {
        this.clientNum = clientNum;
    }

    public PlayerStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(PlayerStatus clientStatus) {
        this.clientStatus = clientStatus;
    }
    public void setClientAction(PlayerAction clientAction){
        this.clientAction = clientAction;
    }
    public PlayerAction getClientAction(){
        return clientAction;
    }

    private Map<String,String> getClientLists(){
        Map<String,String> clientLists = new HashMap<>();
        List<EachConnection> clients = ServerState.getClientInstance().getConnectedClients();
        for (EachConnection client : clients){
            clientLists.put(client.getClientName(),client.getClientStatus().toString());
        }
        return clientLists;
    }


    private Map<String,String> getPlayerList (){
        Map<String,String> playerList = new HashMap<>();
        GameRoom game = getCurrentGame();
        EachConnection[] players= game.getPlayerList();
        for (int i = 0; i < game.getNumOfPlayer(); i++) {
            playerList.put(players[i].getClientName(),
                    players[i].getClientStatus().toString());
        }
        return playerList;
    }


    private void updateGameList(){
        List<GameRoom> gameRooms = ServerState.getGameInstance().getConnectedGames();
        for (GameRoom gameRoom : gameRooms){
            ServerState.gameList.put(gameRoom.getTableId(),gameRoom.getNumOfPlayer());
        }
    }

    private GameRoom getCurrentGame (){
        List<GameRoom> gameRooms = ServerState.getGameInstance().getConnectedGames();
        int index = tableIndex(tableId,gameRooms);
        return gameRooms.get(index);
    }

    private void gameResult(String result){
        Message toPlayers = new Message();
        GameRoom game = getCurrentGame();
        toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
        toPlayers.setGameStatus(GameStatus.ENDING);
        toPlayers.setGameResult(result);
        EachConnection[] players = game.getPlayerList();
        roombroadCast(players,toPlayers);

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
