package org.example.placements;

import org.example.Utils;
import org.example.boards.TicTacBoard;
import org.example.game.Cell;
import org.example.game.Player;

import java.util.Optional;

public class OpponentForkPlacement implements Placement{
    private static OpponentForkPlacement opponentForkPlacement;

    private OpponentForkPlacement() {}

    //singleton pattern
    public synchronized static OpponentForkPlacement get() {
        opponentForkPlacement = (OpponentForkPlacement) Utils.ifNotNull(opponentForkPlacement, OpponentForkPlacement::new);
        return opponentForkPlacement;
    }

    @Override
    public Optional<Cell> getMove(Player player, TicTacBoard board) {
        return ForkPlacement.get().getMove(player, board);
    }

    @Override
    public Placement next() {
        return CenterPlacement.get();
    }
}
