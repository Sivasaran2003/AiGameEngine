package org.example.boards;

public class BoardProxy {
    private final String representation;

    public BoardProxy(TicTacBoard board) {
        representation = board.toString();
    }

    public String getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
