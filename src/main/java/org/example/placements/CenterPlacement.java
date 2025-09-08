package org.example.placements;

import org.example.Utils;
import org.example.boards.TicTacBoard;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.Player;

import java.util.Optional;

public class CenterPlacement implements Placement{
    private static CenterPlacement centerPlacement;

    private CenterPlacement() {}

    //singleton pattern
    public synchronized static CenterPlacement get() {
        centerPlacement = (CenterPlacement) Utils.ifNotNull(centerPlacement, CenterPlacement::new);
        return centerPlacement;
    }

    @Override
    public Optional<Cell> getMove(Player player, TicTacBoard board) {
        Optional<Cell> cell = Optional.empty();
        if(board.getCell(1, 1) == null) cell =  Optional.of(new Cell(1, 1));
        return cell;
    }

    @Override
    public Placement next() {
        return CornerPlacement.get();
    }
}
