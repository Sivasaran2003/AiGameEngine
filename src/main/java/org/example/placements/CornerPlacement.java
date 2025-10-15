package org.example.placements;

import org.example.Utils;
import org.example.boards.TicTacBoard;
import org.example.game.Cell;
import org.example.game.Player;

import java.util.Optional;

public class CornerPlacement implements Placement{

    private static CornerPlacement cornerPlacement;

    private CornerPlacement() {}

    //singleton pattern
    public synchronized static CornerPlacement get() {
        cornerPlacement = (CornerPlacement) Utils.ifNotNull(cornerPlacement, CornerPlacement::new);
        return cornerPlacement;
    }

    @Override
    public Optional<Cell> getMove(Player player, TicTacBoard board) {
        int[][] corners = new int[][]{{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        Optional<Cell> cell = Optional.empty();

        for(int i = 0; i < 4; i++) {
            if(board.getCell(corners[i][0], corners[i][1]) != null)
                cell = Optional.of(Cell.getCell(corners[i][0], corners[i][1]));
        }

        return cell;
    }

    @Override
    public Placement next() {
        return null;
    }
}
