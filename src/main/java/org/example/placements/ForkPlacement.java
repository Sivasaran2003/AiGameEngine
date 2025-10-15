package org.example.placements;

import org.example.Utils;
import org.example.boards.TicTacBoard;
import org.example.game.Cell;
import org.example.game.GameInfo;
import org.example.game.Move;
import org.example.game.Player;

import java.util.Optional;

public class ForkPlacement implements Placement{
    private static ForkPlacement forkPlacement;

    private ForkPlacement() {}

    //singleton pattern
    public synchronized static ForkPlacement get() {
        forkPlacement = (ForkPlacement) Utils.ifNotNull(forkPlacement, ForkPlacement::new);
        return forkPlacement;
    }

    public Cell getForkCell(TicTacBoard board, Player player) { // getting fork cell, if current player makes move in that cell, he would be the winner
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if(board.getCell(row, col) != null) continue;
                TicTacBoard boardCopy = board.copy();
                boardCopy = boardCopy.move(new Move(player, Cell.getCell(row, col)));
                GameInfo gameInfo = ruleEngine.getInfo(boardCopy, player.flip());
                if(gameInfo.isFork()) {
                    return Cell.getCell(row, col);
                }
            }
        }
        return null;
    }

    @Override
    public Optional<Cell> getMove(Player player, TicTacBoard board) {
        return Optional.ofNullable(getForkCell(board, player));
    }

    @Override
    public Placement next() {
        return OpponentForkPlacement.get();
    }
}
