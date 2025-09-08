package org.example.api;

import org.example.game.Board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;


// class for storing Rules for a specific board
public class RuleSet<T extends Board> implements Iterable<Rule<T>> {
    private final List<Rule<T>> rules;

    public RuleSet() {
        this.rules = new ArrayList<>();
    }

    public void add(Rule<T> rule) {
        rules.add(rule);
    }

    @Override
    public Iterator<Rule<T>> iterator() {
        return rules.iterator();
    }

    @Override
    public void forEach(Consumer<? super Rule<T>> action) {
        rules.forEach(action);
    }

    @Override
    public Spliterator<Rule<T>> spliterator() {
        return rules.spliterator();
    }
}
