package io.dev.aoc_2022.day11;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Monkey {

    private final Function<Integer, Integer> worryLevelEvolvler;

    private final Predicate<Integer> test;
    private Monkey trueMonkey, falseMonkey;

    private final List<Integer> items = new LinkedList<>();

    private long numberOfInspections;

    public Monkey(Function<Integer, Integer> worryLevelEvolvler, Predicate<Integer> test, List<Integer> items) {
        this.worryLevelEvolvler = worryLevelEvolvler;
        this.test = test;
        this.items.addAll(items);
    }

    public void setTargetMonkeys(Monkey trueMonkey, Monkey falseMonkey) {
        this.trueMonkey = trueMonkey;
        this.falseMonkey = falseMonkey;
    }

    void playRound() {
        numberOfInspections += items.size();

        items.stream()
                .map(worryLevelEvolvler)
                .map(w -> w/3)
                .forEach(this::throwItem);

        items.clear();
    }

    void throwItem(Integer item) {
        if(test.test(item)) {
            trueMonkey.catchItem(item);
        } else {
            falseMonkey.catchItem(item);
        }
    }

    public void catchItem(Integer item) {
        items.add(item);
    }

    public long getNumberOfInspections() {
        return numberOfInspections;
    }

    @Override
    public String toString() {
        return items.toString();
    }

    public static Comparator<Monkey> compareByInspections = Comparator.comparingLong(Monkey::getNumberOfInspections);
}

