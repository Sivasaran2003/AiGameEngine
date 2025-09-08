package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.*;
import org.example.placements.OffensePlacement;
import org.example.placements.Placement;

import java.util.Optional;

public class AIPlayer extends Player {

    RuleEngine ruleEngine = new RuleEngine();
    public AIPlayer(String playerSymbol) {
        super(playerSymbol);
    }

    public Move suggestMove(Board board, Player player) {
        if(board instanceof TicTacBoard) {
            int threshold = 3;
            Move suggestion = null;

            // based on threshold making specific move
            if(countFilledCells(board) < threshold) suggestion = new Move(player, getBasicMoveCell(board));
            else if(countFilledCells(board) < threshold + 1) suggestion = new Move(player, getSmartMoveCell(board, player));
            else suggestion = new Move(player, getOptimalMove((TicTacBoard) board, player));
            if(suggestion == null) throw new IllegalStateException();
            return suggestion;
        }else throw new IllegalArgumentException();
    }


    private Cell getOptimalMove(TicTacBoard board, Player player) {
        Placement placement = OffensePlacement.get();
        while(placement.next() != null) {
            Optional<Cell> nextMove = placement.getMove(player, board);
            if(nextMove.isPresent()) return nextMove.get();
            placement = placement.next();
        }
        return null;
    }

    private int countFilledCells(Board board) {
        if(board instanceof TicTacBoard) {
            int cnt = 0;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(((TicTacBoard) board).getCell(i, j) != null) cnt++;
                }
            }
            return cnt;
        }else throw new IllegalArgumentException();
    }

    private Cell getSmartMoveCell(Board board, Player player) {
        if(board instanceof TicTacBoard) {
            Cell best = getSmartMoveCell(board, player);
            if(best != null) return best;

            return getBasicMoveCell(board);
        }else throw new IllegalArgumentException();
    }

    private Cell getBasicMoveCell(Board board) {
        // gets the next available cell for the current player to make move [first fit]
        if(board instanceof TicTacBoard) {
            TicTacBoard ticTacBoard = (TicTacBoard) board;
            int row = -1, col = -1;
            for(int i = 0; i < 3 ;i++) {
                for(int j = 0; j < 3; j++) {
                    if(ticTacBoard.getCell(i, j) == null) {
                        row = i; col = j;
                        break;
                    }
                }
                if(row != -1 && col != -1) break;
            }

            return new Cell(row, col);
        }else throw new IllegalArgumentException();
    }
}