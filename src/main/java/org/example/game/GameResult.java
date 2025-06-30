package org.example;

public class GameResult {
    String winner;
    boolean isOver;

    public GameResult(boolean isOver, String winner) {
        this.isOver = isOver;
        this.winner = winner;
    }
}
