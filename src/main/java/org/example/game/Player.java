package org.example.game;

import java.util.Objects;

public class Player {
    private final String playerSymbol;
    private int timeTaken;
    public Player(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public String getPlayerSymbol() {
        return this.playerSymbol;
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
