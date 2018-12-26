package com.model.player;

import javafx.beans.property.SimpleStringProperty;


public class Player {
    private final SimpleStringProperty username;
    private final SimpleStringProperty status;

    public Player(String username, String status){
        this.username = new SimpleStringProperty(username);
        this.status = new SimpleStringProperty(status);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }


}
