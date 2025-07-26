package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.Board;
import org.example.game.GameState;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    public GameState traversal(Function<Integer, String> traversal) {
        boolean streak = true;
        GameState result = new GameState(false, "-");
        for(int j = 0; j < 3; j++) {
            if(traversal.apply(j) == null || !traversal.apply(j).equals(traversal.apply(0))) {
                streak = false;
                break;
            }
        }
        if(streak) result = new GameState(true, traversal.apply(0));
        return result;
    }

    public GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            final int ii = i;
            GameState state = traversal(j -> next.apply(ii, j));
            if(state.isGameOver()) {
                result = state;
            }
        }
        return result;
    }

    public GameState getState(Board board) {
        if (board instanceof TicTacBoard ticTacBoard) {
            GameState rowWin = outerTraversal((i, j) -> ((TicTacBoard) board).getCell(i, j));
            if(rowWin.isGameOver()) return rowWin;

            GameState colWin = outerTraversal((i, j) -> ((TicTacBoard) board).getCell(j, i));
            if(colWin.isGameOver()) return colWin;

            GameState DiagWin = traversal((i) -> ((TicTacBoard) board).getCell(i, i));
            if(DiagWin.isGameOver()) return DiagWin;

            GameState revDiagWin = traversal((i) -> ((TicTacBoard) board).getCell(i, 2 - i));
            if(revDiagWin.isGameOver()) return revDiagWin;

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
