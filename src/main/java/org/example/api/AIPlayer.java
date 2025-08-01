package org.example.api;

import org.example.boards.TicTacBoard;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.Player;

public class AIPlayer extends Player {
    public AIPlayer(String playerSymbol) {
        super(playerSymbol);
    }

    public Move suggestMove(Board board, Player player) {
        if(board instanceof TicTacBoard) {
            TicTacBoard ticTacBoard = (TicTacBoard) board;
            if(smartMove(board, player) == null) return basicMove(board, player);
            return smartMove(board, player);
        }else throw new IllegalArgumentException();
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

    private Move smartMove(Board board, Player player) {
        if(board instanceof TicTacBoard) {
            TicTacBoard boardCopy = ((TicTacBoard) board).copy();
            RuleEngine ruleEngine = new RuleEngine();
            //winning move
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(boardCopy.getCell(i, j) != null) continue;
                    boardCopy.setCell(i, j, player.getPlayerSymbol());
                    if(ruleEngine.getState(boardCopy).isGameOver()) {
                        return new Move(player, new Cell(i, j));
                    }
                    boardCopy.setCell(i, j, null);
                }
            }
            boardCopy = ((TicTacBoard) board).copy();
            //blocking move
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(boardCopy.getCell(i, j) != null) continue;
                    boardCopy.setCell(i, j, player.flip().getPlayerSymbol());
                    if(ruleEngine.getState(boardCopy).isGameOver()) {
                        return new Move(player, new Cell(i, j));
                    }
                    boardCopy.setCell(i, j, null);
                }
            }

            return null;
        }else throw new IllegalArgumentException();
    }

    private Move basicMove(Board board, Player player) {
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

            return new Move(player, new Cell(row, col));
        }else throw new IllegalArgumentException();
    }
}