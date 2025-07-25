package org.example.game;

public class GameState {
    private String winner;
    private boolean isOver;

    public GameState(boolean isOver, String winner) {
        this.isOver = isOver;
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }

    public boolean isGameOver() {
        return isOver;
    }
}
