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

    public GameInfo getInfo(Board board, Player currPlayer) {
        if(board instanceof TicTacBoard) {
            int winningMoves = 0;
            GameState state = getState(board);
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    TicTacBoard temp = ((TicTacBoard) board).copy();
                    if(temp.getCell(i, j) != null) continue;
                    temp.move(new Move(currPlayer.flip(), new Cell(i, j)));

                    if(getState(temp).getWinner().equals(currPlayer.flip().getPlayerSymbol())) {
                        winningMoves++;
                    }
                }
            }

            if(winningMoves >= 2) return new GameInfoBuilder()
                    .isOver(true)
                    .winner(currPlayer.flip().getPlayerSymbol())
                    .fork(true).build();

            return new GameInfoBuilder()
                    .isOver(state.isGameOver())
                    .winner(state.getWinner())
                    .fork(false).build();
        }

        throw new IllegalArgumentException();
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