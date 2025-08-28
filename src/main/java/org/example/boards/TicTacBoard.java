package org.example.boards;

import org.example.api.Rule;
import org.example.api.RuleSet;
import org.example.game.Board;
import org.example.game.GameState;
import org.example.game.Move;

import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacBoard implements Board {
    public String[][] cells;

    public TicTacBoard() {
        cells = new String[3][3];
    }

    @Override
    public void move(Move move) {
        cells[move.getSymbol().getX()][move.getSymbol().getY()] = move.getPlayer().getPlayerSymbol();
    }

    public String getCell(int x, int y) {
        return cells[x][y];
    }
    public void setCell(int x, int y, String symbol) {
        cells[x][y] = symbol;
    }

    public static GameState traversal(Function<Integer, String> traversal) {
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

    public static GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            final int ii = i;
            GameState state = traversal(j -> next.apply(ii, j));
            if(state.isGameOver()) {
                result = state;
                break;
            }
        }
        return result;
    }

    public static RuleSet<TicTacBoard> getRules() {
        RuleSet rules = new RuleSet();
        rules.add(new Rule<TicTacBoard>(board -> outerTraversal(board::getCell)));
        rules.add(new Rule<TicTacBoard>(board -> outerTraversal((i, j) -> board.getCell(j, i))));
        rules.add(new Rule<TicTacBoard>(board -> traversal(i -> board.getCell(i, i))));
        rules.add(new Rule<TicTacBoard>(board -> traversal(i -> board.getCell(i, 2 - i))));
        rules.add(new Rule<TicTacBoard>(board -> {
            int countFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getCell(i, j) != null) {
                        countFilledCells++;
                    }
                }
            }
            if (countFilledCells != 9) {
                return new GameState(false, "-");
            }
            return new GameState(true, "-");
        }));

        return rules;
    }

    public void print() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(getCell(i, j) == null) System.out.print("- ");
                else System.out.print(getCell(i, j) + " ");
            }
            System.out.println();
        }
    }

    @Override
    public TicTacBoard copy() {
        TicTacBoard board = new TicTacBoard();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board.setCell(i, j, this.getCell(i, j));
            }
        }

        return board;
    }

    @Override
    public String toString() {
        StringBuilder brd = new StringBuilder();
        for (String[] row : cells) {
            for (String cell : row) {
                brd.append(cell == null ? "- " : cell + " ");
            }
            brd.append("\n");
        }

        return String.valueOf(brd);
    }
}
