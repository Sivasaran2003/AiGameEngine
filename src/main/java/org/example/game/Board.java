package org.example.game;

public interface Board {
    public abstract void move(Move move);
    public Board copy();
}
