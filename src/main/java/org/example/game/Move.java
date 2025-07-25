package org.example.game;

public class Move {
    private Cell cell;
    private Player player;

    public Move(Player player, Cell cell) {
        this.player = player;
        this.cell = cell;
    }

    public Player getPlayer() {
        return player;
    }

    public Cell getSymbol() {
        return this.cell;
    }

    @Override
    public String toString() {
        return cell.getX() + ", " + cell.getY() + "\n";
    }
}
