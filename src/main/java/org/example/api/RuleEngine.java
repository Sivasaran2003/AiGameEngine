package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    Map<String, List<Rule>> rules = new HashMap<>();// (i, j) -> ((TicTacBoard) board).getCell(i, j) same as getCell func (int, int) -> string
    private Function<TicTacBoard, GameState> rowTraversal = board -> outerTraversal(board::getCell);
    private Function<TicTacBoard, GameState> colTraversal = board -> outerTraversal((i, j) -> board.getCell(j, i));
    private Function<TicTacBoard, GameState> diagTraversal = board -> traversal(i -> ((TicTacBoard) board).getCell(i, i));
    private Function<TicTacBoard, GameState> diagRevTraversal = board -> traversal(i -> ((TicTacBoard) board).getCell(i, 2 - i));
    private Function<TicTacBoard, GameState> countMoves = board -> {
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
    };

    public RuleEngine() {
        rules.put(TicTacBoard.class.getName(), new ArrayList<>());
        rules.get(TicTacBoard.class.getName()).add(new Rule<TicTacBoard>(board -> rowTraversal.apply(board)));
        rules.get(TicTacBoard.class.getName()).add(new Rule<TicTacBoard>(board -> colTraversal.apply(board)));
        rules.get(TicTacBoard.class.getName()).add(new Rule<TicTacBoard>(board -> diagTraversal.apply(board)));
        rules.get(TicTacBoard.class.getName()).add(new Rule<TicTacBoard>(board -> diagRevTraversal.apply(board)));
        rules.get(TicTacBoard.class.getName()).add(new Rule<TicTacBoard>(board -> countMoves.apply(board)));
    }

    public GameInfo getInfo(Board board) {
        if (board instanceof TicTacBoard) {
            Player[] players = {new Player("X"), new Player("O")};
            Player winner;
            for (Player player : players) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        TicTacBoard b = ((TicTacBoard) board).copy();
                        if (b.getCell(i, j) != null) continue;
                        b.move(new Move(player, new Cell(i, j)));
                        boolean stillWins = true;
                        for (int l = 0; l < 3; l++) {
                            for (int m = 0; m < 3; m++) {
                                TicTacBoard b1 = b.copy();
                                if (b1.getCell(l, m) != null) continue;
                                b1.move(new Move(player.flip(), new Cell(l, m)));
                                if (getState(b1).isGameOver()) {
                                    stillWins = false;
                                    break;
                                }
                            }
                            if (!stillWins) break;
                        }
                        if (stillWins) return new GameInfo(getState(board), player, true);
                    }
                }

                return new GameInfo(getState(board), player, true);
            }
        }else throw new IllegalArgumentException();
        return null;
    }

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
                break;
            }
        }
        return result;
    }

    public GameState getState(Board board) {
        if (board instanceof TicTacBoard ticTacBoard) {
            for(Rule<TicTacBoard> rule : rules.get(TicTacBoard.class.getName())) {
                GameState apply = rule.condition.apply(ticTacBoard);
                if(apply.isGameOver()) {
                    return apply;
                }
            }

            return new GameState(false, "-");
        }else throw new IllegalArgumentException();
    }
}

class Rule<T extends Board> {
     Function<T, GameState> condition;

     public Rule(Function<T, GameState> condition) {
         this.condition = condition;
     }
}