package org.example.game;

public class Cell {
    private int x, y;
    static Cell[][] cells = new Cell[3][3];
    private Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Cell getCell(int x, int y) {
        if(cells[x][y] == null) {
            return new Cell(x, y);
        } else return cells[x][y];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
