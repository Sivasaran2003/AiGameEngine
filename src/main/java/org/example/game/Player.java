package org.example.game;

import org.example.api.User;

import java.util.Objects;

public class Player {
    private final String playerSymbol;
    private int timeTaken;
    private User user;
    public Player(String playerSymbol) {
        this.playerSymbol = playerSymbol;
        user = new User();
    }

    public String getPlayerSymbol() {
        return this.playerSymbol;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(int timeTaken) {
        this.timeTaken = timeTaken;
    }

    public Player flip() {
        return Objects.equals(playerSymbol, "O") ? new Player("X") : new Player("O");
    }
}
