package org.example.placements;

import org.example.Utils;
import org.example.boards.TicTacBoard;
import org.example.game.Cell;
import org.example.game.Player;

import java.util.Optional;

public class OffensePlacement implements Placement{
    private static OffensePlacement offensePlacement;

    private OffensePlacement() {}

    //singleton pattern
    public synchronized static OffensePlacement get() {
        offensePlacement = (OffensePlacement) Utils.ifNotNull(offensePlacement, OffensePlacement::new);
        return offensePlacement;
    }

    public Cell getWinningMoveCell(TicTacBoard board, Player player) {

        TicTacBoard boardCopy = ((TicTacBoard) board).copy();
        //winning move for current player
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(boardCopy.getCell(i, j) != null) continue;
                boardCopy.setCell(i, j, player.getPlayerSymbol());
                if(ruleEngine.getState(boardCopy).isGameOver()) {
                    return Cell.getCell(i, j);
                }
                boardCopy.setCell(i, j, null);
            }
        }

        return null;
    }

    @Override
    public Optional<Cell> getMove(Player player, TicTacBoard board) {
        return Optional.ofNullable(getWinningMoveCell(board, player));
    }

    @Override
    public Placement next() {
        return DefensePlacement.get();
    }
}
