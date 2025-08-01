package org.example.game;

public class GameInfo {
    private GameState gameState;
    private Player currentPlayer;
    private boolean fork;

    public GameInfo(GameState gameState, Player currentPlayer, boolean hasFork) {
        this.gameState = gameState;
        this.fork = hasFork;
        this.currentPlayer = currentPlayer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public boolean isFork() {
        return fork;
    }
}
