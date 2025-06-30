package org.example;

import org.example.game.Board;

public class TicTacBoard extends Board {
    public String[][] cells;

    public TicTacBoard() {
        cells = new String[3][3];
    }
}
