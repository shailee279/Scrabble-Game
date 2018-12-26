package com.game;

import com.messages.PlayerStatus;
import com.server.EachConnection;
import com.server.ServerState;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.*;

public class GameRoom {
    // assuming there are up to 4 players
    private static int MAXIMUM_PLAYER_NUMBER = 4;
    private static int MINIMUM_PLAYER_NUMBER = 2;
    // tracking number of total players in one game room
    private int numOfPlayer;
    private int tableId;
    private EachConnection[] playerList = new EachConnection[MAXIMUM_PLAYER_NUMBER];
    private InputStream in;
    private OutputStream out;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private int votingYes = 0;
    private int spaceRemain = 400;
    private int votingNum = 0;
    private int passNum = 0;
    private int totalTurn = 0;
    private boolean ending = false;
    private Map<String, String> playerStatus = new HashMap();
    private String[] board;
    private Map<String, Integer> playerScore = new HashMap();
    private boolean gameStart = false;
    private ArrayList<String> sequenceList = new ArrayList<>();

    public GameRoom(int clientNum, int tableId) {
        addPlayer(clientNum);
        initialBoard();
        this.tableId = tableId;
    }

    public synchronized void initialGame() {
        addOneTurn();
        for (String key : playerStatus.keySet()) {
            playerStatus.replace(key, "NotTurn");
        }
        playerStatus.replace(playerList[0].getClientName(), "Turn");
    }

    public void initialBoard() {
        board = new String[400];
        for (int i = 0; i < 400; i++) {
            board[i] = "";
        }
    }

    public void addCharacter(int index, String character) {
        board[index] = character;
    }

    public synchronized String[] getBoard() {
        return board;
    }

    public void setPlayerScore(String name, Integer score) {
        playerScore.replace(name, score);
    }

    public synchronized Map<String, Integer> getPlayerScore() {
        return playerScore;
    }

    public synchronized void addPlayer(int clientNum) {
        List<EachConnection> clients = ServerState.getClientInstance().getConnectedClients();
        for (EachConnection client : clients) {
            if (client.getClientNum() == clientNum) {
                playerList[numOfPlayer] = client;
                playerStatus.put(client.getClientName(), "NotReady");
                playerScore.put(client.getClientName(), 0);
                // add name to the sequence list
                sequenceList.add(client.getClientName());
            }
        }
        this.numOfPlayer += 1;
    }

    public synchronized int getScore(String name) {
        return playerScore.get(name);
    }

    public synchronized void deletePlayer(int clientNum, String name) {
        int index = indexOf(clientNum);
        playerStatus.remove(name);
        playerScore.remove(name);
        this.numOfPlayer -= 1;
        // remove name from the sequence list
        sequenceList.remove(name);
        if (index != -1) {
            playerList[index] = null;
            for (int x = 0; x < numOfPlayer; x++) {
                if (x >= index) {
                    playerList[x] = playerList[x + 1];
                }
            }
            playerList[numOfPlayer] = null;
        }
    }

    public synchronized void playerReady(String name) {
        playerStatus.replace(name, "Ready");
    }

    public synchronized void turnPass(String name) {
        int index = sequenceList.indexOf(name) + 1;
        if (index > numOfPlayer - 1) {
            index = 0;
        }
        playerTurn(sequenceList.get(index));
    }

    public synchronized void playerTurn(String name) {
        playerStatus.replace(name, "Turn");
        for (String key : playerStatus.keySet()) {
            if (!key.equals(name)) {
                playerStatus.replace(key, "NotTurn");
            }
        }
    }

    public synchronized Map getPlayerStatus() {
        return playerStatus;
    }

    private int indexOf(int clientNum) {
        for (int i = 0; i < numOfPlayer; i++) {
            if (playerList[i].getClientNum() == clientNum) {
                return i;
            }
        }
        return -1;
    }

    public synchronized String votingResult() {
        if (votingNum == numOfPlayer) {
            if (votingYes == numOfPlayer) {
                return "Accept";
            } else {
                return "Reject";
            }
        } else {
            return "inProgress";
        }
    }

    public synchronized String passResult() {
        if (passNum >= numOfPlayer) {
            return "GameEnd";
        } else {
            return "GameContinue";
        }
    }

    public synchronized boolean gameEnd() {
        if (numOfPlayer == 1 || spaceRemain == 0) {
            return true;
        }
        return false;
    }

    //TODO gameResult format
    public synchronized String gameResult() {
        String winner = "";
        int max = 0;
        List<String> winnerList = new ArrayList<>();
        for (String key : playerScore.keySet()) {
            if (max < playerScore.get(key)) {
                max = playerScore.get(key);
            }
        }
        for (String key : playerScore.keySet()) {
            if (playerScore.get(key) == max) {
                winnerList.add(key);
            }
        }
        for (int i = 0; i < winnerList.size(); i++) {
            winner = winner + winnerList.get(i) + " & ";
        }
        winner = winner.substring(0,winner.length()-3);
        ending = true;
        return winner;
    }



    public int getTableId() {
        return tableId;
    }

    public synchronized int getNumOfPlayer() {
        return numOfPlayer;
    }

    public static int getMaximumPlayerNumber() {
        return MAXIMUM_PLAYER_NUMBER;
    }

    public static int getMinimumPlayerNumber() {
        return MINIMUM_PLAYER_NUMBER;
    }

    public void setNumOfPlayer(int numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getSpaceRemain() {
        return spaceRemain;
    }

    public void SpaceRemain() {
        this.spaceRemain -= 1;
    }

    public EachConnection[] getPlayerList() {
        return playerList;
    }

    public void setPlayerList(EachConnection[] playerList) {
        this.playerList = playerList;
    }

    public int getVotingNum() {
        return votingNum;
    }

    public synchronized void voting(boolean voting) {
        this.votingNum += 1;
        if (voting){
            this.votingYes+= 1;
        }
    }

    public synchronized void pass() {
        this.passNum += 1;
    }

    public int getTotalTurn() {
        return totalTurn;
    }

    public void setTotalTurn(int totalTurn) {
        this.totalTurn = totalTurn;
    }

    public void addOneTurn() {
        this.totalTurn += 1;
    }

    public boolean isGameStart() {
        return gameStart;
    }

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    public int getVotingYes() {
        return votingYes;
    }

    public void setVotingYes(int votingYes) {
        this.votingYes = votingYes;
    }

    public void setVotingNum(int votingNum) {
        this.votingNum = votingNum;
    }

    public int getPassNum() {
        return passNum;
    }

    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    public boolean isEnding() {
        return ending;
    }

    public void setEnding(boolean ending) {
        this.ending = ending;
    }
}