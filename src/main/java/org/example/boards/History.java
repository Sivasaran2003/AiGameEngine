package org.example.boards;

import java.util.ArrayList;
import java.util.List;

public class History {
    private final List<BoardProxy> boards;

    public History() {
        boards = new ArrayList<>();
    }

    public void put(BoardProxy board) {
        boards.add(board);
    }

    public BoardProxy undo() {
        if (boards.isEmpty()) throw new IllegalStateException();

        boards.remove(boards.size() - 1);
        return boards.get(boards.size() - 1);
    }

    public BoardProxy getNthMove(int index) {
        for (int i = boards.size() - 1; i > index; i--) {
            boards.remove(i);
        }
        return boards.get(boards.size() - 1);
    }

    public List<BoardProxy> getBoards() {
        return boards;
    }
}
