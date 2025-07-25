package org.example.boards;

import org.example.game.Board;
import org.example.game.Move;

public class TicTacBoard implements Board {
    public String[][] cells;

    public TicTacBoard() {
        cells = new String[3][3];
    }

    @Override
    public void move(Move move) {
        cells[move.getSymbol().getX()][move.getSymbol().getY()] = move.getPlayer().getPlayerSymbol();
    }

    public String getCell(int x, int y) {
        return cells[x][y];
    }
    public void setCell(int x, int y, String symbol) {
        cells[x][y] = symbol;
    }

    public void print() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(getCell(i, j) == null) System.out.print("- ");
                else System.out.print(getCell(i, j) + " ");
            }
            System.out.println();
        }
    }

    @Override
    public TicTacBoard copy() {
        TicTacBoard board = new TicTacBoard();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board.setCell(i, j, this.getCell(i, j));
            }
        }

        return board;
    }

    @Override
    public String toString() {
        StringBuilder brd = new StringBuilder();
        for (String[] row : cells) {
            for (String cell : row) {
                brd.append(cell == null ? "- " : cell + " ");
            }
            brd.append("\n");
        }

        return String.valueOf(brd);
    }
}
