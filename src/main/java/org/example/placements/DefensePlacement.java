package org.example.placements;

import org.example.Utils;
import org.example.boards.TicTacBoard;
import org.example.game.Cell;
import org.example.game.Player;

import java.util.Optional;

public class DefensePlacement implements Placement{
    private static DefensePlacement defensePlacement;

    private DefensePlacement() {}

    //singleton pattern
    public synchronized static DefensePlacement get() {
        defensePlacement = (DefensePlacement) Utils.ifNotNull(defensePlacement, DefensePlacement::new);
        return defensePlacement;
    }

    public Cell getBlockingMoveCell(TicTacBoard board, Player player) {
        TicTacBoard boardCopy = ((TicTacBoard) board).copy();
        //blocking move for current player, to stop opponent from winning
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(boardCopy.getCell(i, j) != null) continue;
                boardCopy.setCell(i, j, player.flip().getPlayerSymbol());
                if(ruleEngine.getState(boardCopy).isGameOver()) {
                    return new Cell(i, j);
                }
                boardCopy.setCell(i, j, null);
            }
        }
        return null;
    }

    @Override
    public Optional<Cell> getMove(Player player, TicTacBoard board) {
        return Optional.ofNullable(getBlockingMoveCell(board, player));
    }

    @Override
    public Placement next() {
        return ForkPlacement.get();
    }
}
