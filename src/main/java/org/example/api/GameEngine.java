package org.example.api;

import org.example.boards.BoardProxy;
import org.example.boards.History;
import org.example.boards.TicTacBoard;
import org.example.game.Board;
import org.example.game.Move;

public class GameEngine {
    private final History history;

    public GameEngine() {
        history = new History();
    }

    public History getHistory() {
        return history;
    }

    public Board start(String type) {
        if (type.equals("TicTacBoard")) {
            return new TicTacBoard();
        } else throw new IllegalArgumentException();
    }

    public Board move(Board board, Move move) {
        if(board instanceof TicTacBoard ticTacBoard) {
            ticTacBoard = ticTacBoard.move(move);
            history.put(new BoardProxy(ticTacBoard));
        }else {
            throw new IllegalArgumentException();
        }

        return ticTacBoard;
    }

}
