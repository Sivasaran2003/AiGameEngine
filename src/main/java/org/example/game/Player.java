package org.example.game;

import java.util.Objects;

public class Player {
    private String playerSymbol;
    public Player(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public String getPlayerSymbol() {
        return this.playerSymbol;
    }

    public Player flip() {
        return Objects.equals(playerSymbol, "O") ? new Player("X") : new Player("O");
    }
}
