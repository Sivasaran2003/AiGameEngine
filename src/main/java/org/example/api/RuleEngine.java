package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.Board;
import org.example.game.GameState;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    public GameState isVictory(BiFunction<Integer, Integer, String> next) {
        boolean streak;
        for (int i = 0; i < 3; i++) {
            streak = true;
            for(int j = 0; j < 3; j++) {
                if(next.apply(i, j) == null || !next.apply(i, j).equals(next.apply(i, 0))) {
                    streak = false;
                    break;
                }
            }
            if(streak) return new GameState(true, next.apply(i, 0));
        }
        return null;
    }

    public GameState isVictoryDiag(Function<Integer, String> next) {
        boolean streak = true;
        for (int i = 0; i < 3; i++) {
            if (next.apply(i) == null || !next.apply(0).equals(next.apply(i))) {
                streak = false;
                break;
            }
        }
        if (streak) return new GameState(true, next.apply(0));
        return null;
    }

    public GameState getState(Board board) {
        if (board instanceof TicTacBoard ticTacBoard) {
            GameState rowWin = isVictory((i, j) -> ((TicTacBoard) board).getCell(i, j));
            if(rowWin != null) return rowWin;

            GameState colWin = isVictory((i, j) -> ((TicTacBoard) board).getCell(j, i));
            if(colWin != null) return colWin;

            GameState DiagWin = isVictoryDiag((i) -> ((TicTacBoard) board).getCell(i, i));
            if(DiagWin != null) return DiagWin;

            GameState revDiagWin = isVictoryDiag((i) -> ((TicTacBoard) board).getCell(i, 2 - i));
            if(revDiagWin != null) return revDiagWin;

            // Check for incomplete game
            int countFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacBoard.getCell(i, j) != null) {
                        countFilledCells++;
                    }
                }
            }

            if (countFilledCells != 9) {
                return new GameState(false, "-");
            }

            return new GameState(true, "-"); // Draw
        }

        return new GameState(false, "-");
    }
}
