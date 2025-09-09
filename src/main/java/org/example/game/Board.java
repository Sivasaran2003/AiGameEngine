package org.example.game;

public interface Board {
    public Board move(Move move);
    public Board copy();
}
