package org.example.game;

import org.example.boards.TicTacBoard;

public class GameCreator {
    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer) {
        return new Game(
                new GameConfig(maxTimePerMove, maxTimePerPlayer != null),
                null,
                new TicTacBoard(),
                0,
                maxTimePerPlayer,
                maxTimePerMove
        );
    }

    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer, Board board) {
        return new Game(
                new GameConfig(maxTimePerMove, maxTimePerPlayer != null),
                null,
                board,
                0,
                maxTimePerPlayer,
                maxTimePerMove
        );
    }

}
