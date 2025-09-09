package org.example.boards;
import org.example.api.Rule;
import org.example.api.RuleSet;
import org.example.game.Board;
import org.example.game.CellBoard;
import org.example.game.GameState;
import org.example.game.Move;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacBoard implements CellBoard {
    public String[][] cells;
    public History history;

    public TicTacBoard() {
        cells = new String[3][3];
        history = new History();
    }

    @Override
    public TicTacBoard move(Move move) {
        TicTacBoard board = this.copy();
        board.setCell(move.getSymbol().getX(), move.getSymbol().getY(), move.getPlayer().getPlayerSymbol());
        history.put(board);
        return board;
    }

    public String getCell(int x, int y) {
        return cells[x][y];
    }
    public void setCell(int x, int y, String symbol) {
        cells[x][y] = symbol;
    }

    // for inner traversal [column traversal] - gets a function with row fixed j -> (row, j)
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

    // for outer traversal [row traversal] - gets a function (i, j) -> symbol
    public static GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            final int ii = i;
            GameState state = traversal(j -> next.apply(ii, j)); // fixing row for column traversal
            if(state.isGameOver()) {
                result = state;
                break;
            }
        }
        return result;
    }

    public static RuleSet getRules() {
        RuleSet rules = new RuleSet();
        // each rule gets a Function where input is a type of Board and return value is GameState
        rules.add(new Rule(board -> outerTraversal(board::getCell))); // row wise check
        rules.add(new Rule(board -> outerTraversal((i, j) -> board.getCell(j, i)))); // column wise check
        rules.add(new Rule(board -> traversal(i -> board.getCell(i, i)))); // diagonal check
        rules.add(new Rule(board -> traversal(i -> board.getCell(i, 2 - i)))); // reverse diagonal check
        rules.add(new Rule(board -> { // game over check
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

class History {
    List<Board> boards;

    public History() {
        boards = new ArrayList<>();
    }

    public void put(Board board) {
        boards.add(board);
    }

    public Board undo() {
        if(boards.isEmpty()) throw new IllegalStateException();

        boards.remove(boards.size() - 1);
        return boards.get(boards.size() - 1);
    }

    public Board getNthMove(int index) {
        for(int i = boards.size() - 1; i > index; i--) {
            boards.remove(i);
        }
        return boards.get(boards.size() - 1);
    }

}