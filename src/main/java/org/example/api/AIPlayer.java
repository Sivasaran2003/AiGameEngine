package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.*;

public class AIPlayer extends Player {

    RuleEngine ruleEngine = new RuleEngine();
    public AIPlayer(String playerSymbol) {
        super(playerSymbol);
    }

    public Move suggestMove(Board board, Player player) {
        if(board instanceof TicTacBoard) {
            int threshold = 3;
            Move suggestion = null;
            if(countFilledCells(board) < threshold) suggestion = new Move(player, getBasicMoveCell(board));
            else if(countFilledCells(board) < threshold + 1) suggestion = new Move(player, getSmartMoveCell(board, player));
            else suggestion = new Move(player, getOptimalMove((TicTacBoard) board, player));
            if(suggestion == null) throw new IllegalStateException();
            return suggestion;
        }else throw new IllegalArgumentException();
    }

    private Cell getForkCell(TicTacBoard board, Player player) {
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if(board.getCell(row, col) != null) continue;
                TicTacBoard boardCopy = board.copy();
                boardCopy.move(new Move(player, new Cell(row, col)));
                GameInfo gameInfo = ruleEngine.getInfo(boardCopy, player.flip());
                if(gameInfo.isFork()) {
                    return new Cell(row, col);
                }
            }
        }
        return null;
    }

    private Cell getOptimalMove(TicTacBoard board, Player player) {
        // if u have winning move
        Cell best = getSmartMoveCell(board, player);
        if(best != null) return best;

        //if opp have winning move
        best = getBlockingMoveCell(board, player);
        if(best != null) return best;

        // if fork make that move
        best = getForkCell(board, player);
        if(best != null) return best;

        // if opp has fork, make that
        best = getForkCell(board, player.flip());
        if(best != null) return best;

        // if center is available, use that
        if(board.getCell(1,1) == null) return new Cell(1, 1);

        // if corner is available then take it
        int[][] corners = new int[][]{{0, 0}, {0, 2}, {2, 0}, {2, 2}};

        for(int i = 0; i < 4; i++) {
            if(board.getCell(corners[i][0], corners[i][1]) != null)
                return new Cell(corners[i][0], corners[i][1]);
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

    Cell getWinningMoveCell(TicTacBoard board, Player player) {

        TicTacBoard boardCopy = ((TicTacBoard) board).copy();
        //winning move
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(boardCopy.getCell(i, j) != null) continue;
                boardCopy.setCell(i, j, player.getPlayerSymbol());
                if(ruleEngine.getState(boardCopy).isGameOver()) {
                    return new Cell(i, j);
                }
                boardCopy.setCell(i, j, null);
            }
        }

        return null;
    }

    Cell getBlockingMoveCell(TicTacBoard board, Player player) {
        TicTacBoard boardCopy = ((TicTacBoard) board).copy();
        //blocking move
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

    private Cell getSmartMoveCell(Board board, Player player) {
        if(board instanceof TicTacBoard) {
            Cell best = getSmartMoveCell(board, player);
            if(best != null) return best;

            return getBasicMoveCell(board);
        }else throw new IllegalArgumentException();
    }

    private Cell getBasicMoveCell(Board board) {
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