package org.example.game;

public class Game {
    GameConfig gameConfig;
    Board board;
    Player winner;
    Integer maxTimePerMove, maxTimePerPlayer, timeTakenSinceLastMove;

    public Game(GameConfig gameConfig, Player winner, Board board, Integer timeTakenSinceLastMove, Integer maxTimePerPlayer, Integer maxTimePerMove) {
        this.winner = winner;
        this.board = board;
        this.gameConfig = gameConfig;
        this.maxTimePerPlayer = maxTimePerPlayer;
        this.maxTimePerMove = maxTimePerMove;
        this.timeTakenSinceLastMove = timeTakenSinceLastMove;
    }

    public void move(Move move, int timeInMilliseconds) {
        timeTakenSinceLastMove = timeInMilliseconds - move.getPlayer().getTimeTaken();
        move.getPlayer().setTimeTaken(timeInMilliseconds);
        if(gameConfig.timed) {
            this.moveForTimedGame(move, timeTakenSinceLastMove);
        } else {
            board = board.move(move);
        }
    }

    private void moveForTimedGame(Move move, int timeTakenSinceLastMove) {
        final int currentTime, endTime;
        if(gameConfig.timed) {
            currentTime = timeTakenSinceLastMove;
            endTime = maxTimePerMove;
        } else {
            currentTime = move.getPlayer().getTimeTaken();
            endTime = maxTimePerPlayer;
        }

        if(currentTime < endTime) board.move(move);
        else winner = move.getPlayer().flip();
    }

    public Player getWinner() {
        return winner;
    }
}
