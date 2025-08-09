package org.example.game;

public class Game {
    GameConfig gameConfig;
    Board board;
    Player winner;
    public void move(Move move, int timeInMilliseconds) {
        int timeTakenSinceLastMove = timeInMilliseconds - move.getPlayer().getTimeTaken();
        move.getPlayer().setTimeTaken(timeInMilliseconds);
        if(gameConfig.timed) {
            if(!isTLE(timeTakenSinceLastMove)) {
                board.move(move);
            }else {
                winner = move.getPlayer().flip();
            }
        } else {
            board.move(move);
        }
    }

    private boolean isTLE(int timeInMilliseconds) {
        return timeInMilliseconds > gameConfig.timePerMove;
    }
}
