package org.example.api;

import org.example.game.Board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;


// class for storing Rules for a specific board
public class RuleSet implements Iterable<Rule> {
    private final List<Rule> rules;

    public RuleSet() {
        this.rules = new ArrayList<>();
    }

    public void add(Rule rule) {
        rules.add(rule);
    }

    @Override
    public Iterator<Rule> iterator() {
        return rules.iterator();
    }

    @Override
    public void forEach(Consumer<? super Rule> action) {
        rules.forEach(action);
    }

    @Override
    public Spliterator<Rule> spliterator() {
        return rules.spliterator();
    }
}
