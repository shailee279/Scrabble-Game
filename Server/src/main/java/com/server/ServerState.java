package com.server;

import com.game.GameRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerState {

    public static Map<String, String> clientList = new HashMap<String, String>();
    public static Map<Integer,Integer> gameList = new HashMap<>();
    private static ServerState clientInstance;
    private static ServerState gameInstance;
    private List<EachConnection> connectedClients;
    private List<GameRoom> createdGames;

    public static ArrayList UserList = new ArrayList();

    private ServerState() {
        connectedClients = new ArrayList<>();
        createdGames = new ArrayList<>();
        UserList.add("Empty");
    }

    public static synchronized ServerState getGameInstance() {
        if (gameInstance == null) {
            gameInstance = new ServerState();
        }
        return gameInstance;
    }

    public synchronized void gameConnected(GameRoom game) {
        createdGames.add(game);
    }

    public synchronized void gameDisconnected(GameRoom game) {
        createdGames.remove(game);
    }

    public synchronized List<GameRoom> getConnectedGames() {
        return createdGames;
    }


    public static synchronized ServerState getClientInstance() {
        if (clientInstance == null) {
            clientInstance = new ServerState();
        }
        return clientInstance;
    }

    public synchronized void clientConnected(EachConnection client) {
        connectedClients.add(client);
    }

    public synchronized void clientDisconnected(EachConnection client) {
        connectedClients.remove(client);
    }

    public synchronized List<EachConnection> getConnectedClients() {
        return connectedClients;
    }

}
