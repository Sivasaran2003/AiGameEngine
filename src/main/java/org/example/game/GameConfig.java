package org.example.game;

public class GameConfig {
    int timePerMove;
    boolean timed;

    public GameConfig(int timePerMove, boolean timed) {
        this.timed = timed;
        this.timePerMove = timePerMove;
    }
}
