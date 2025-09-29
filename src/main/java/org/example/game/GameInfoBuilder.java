package org.example.game;

public class GameInfoBuilder {
    private Player currentPlayer;
    private boolean fork;
    private Player winner;
    private boolean isOver;
    private int numberOfMoves;

    public GameInfoBuilder currentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        return this;
    }

    public GameInfoBuilder fork(boolean fork) {
        this.fork = fork;
        return this;
    }

    public GameInfoBuilder winner(Player winner) {
        this.winner = winner;
        return this;
    }

    public GameInfoBuilder isOver(boolean over) {
        isOver = over;
        return this;
    }

    public GameInfoBuilder numberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
        return this;
    }

    public GameInfo build() {
        return new GameInfo(currentPlayer, fork, isOver, numberOfMoves, winner);
    }
}
