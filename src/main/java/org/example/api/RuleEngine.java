package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.*;

import java.util.HashMap;
import java.util.Map;

public class RuleEngine {

    Map<String, RuleSet> rules = new HashMap<>();

    public RuleEngine() {
        rules.put(TicTacBoard.class.getName(), TicTacBoard.getRules());
    }

    // return more info current player, winner, fork, number of moves, isGameOver
    public GameInfo getInfo(Board board, Player currPlayer) {
        if (board instanceof TicTacBoard) {
            int winningMoves = 0;
            GameState state = getState(board);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    TicTacBoard temp = ((TicTacBoard) board).copy();
                    if (temp.getCell(i, j) != null) continue;
                    temp.move(new Move(currPlayer.flip(), new Cell(i, j)));

                    if (getState(temp).getWinner().equals(currPlayer.flip().getPlayerSymbol())) {
                        winningMoves++;
                    }
                }
            }

            if (winningMoves >= 2) return new GameInfoBuilder()
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

    // applies all the rules for the provided board and returns the current state of the board
    public GameState getState(Board board) {
        if (board instanceof TicTacBoard ticTacBoard) {
            @SuppressWarnings("unchecked")
            RuleSet<TicTacBoard> ticTacRules =
                    (RuleSet<TicTacBoard>) rules.get(TicTacBoard.class.getName());

            for (Rule<TicTacBoard> rule : ticTacRules) {
                GameState apply = rule.condition.apply(ticTacBoard);
                if (apply.isGameOver()) {
                    return apply;
                }
            }
            return new GameState(false, "-");
        }
        throw new IllegalArgumentException("Unsupported board type: " + board.getClass());
    }
}