package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.Board;
import org.example.game.Move;

public class GameEngine {
    public Board start(String type) {
        if (type.equals("TicTacBoard")) {
            return new TicTacBoard();
        } else throw new IllegalArgumentException();
    }

    public void move(Board board, Move move) {
        if(board instanceof TicTacBoard) {
            TicTacBoard ticTacBoard = (TicTacBoard) board;
            ticTacBoard.move(move);
        }else {
            throw new IllegalArgumentException();
        }
    }

}
