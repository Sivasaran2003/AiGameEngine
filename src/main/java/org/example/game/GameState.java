package org.example.game;

public class GameState {
    private Player winner;
    private boolean isOver;

    public GameState(boolean isOver, Player winner) {
        this.isOver = isOver;
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isGameOver() {
        return isOver;
    }
}
