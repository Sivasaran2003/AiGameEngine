package org.example.game;

public class GameInfo {
    private Player currentPlayer;
    private boolean fork;
    private String winner;
    private boolean isOver;

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isFork() {
        return fork;
    }

    public String getWinner() {
        return winner;
    }

    public boolean isOver() {
        return isOver;
    }

    private int numberOfMoves;

    public GameInfo(Player player, boolean fork, boolean isOver, int numberOfMoves, String winner) {
        this.isOver = isOver;
        this.winner = winner;
        this.fork = fork;
        this.currentPlayer = player;
        this.numberOfMoves = numberOfMoves;
    }
}

