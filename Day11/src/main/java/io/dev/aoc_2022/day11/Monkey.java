package io.dev.aoc_2022.day11;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Monkey {

    private final Function<Long, Long> worryLevelEvolvler;

    private final Predicate<Long> test;
    private Monkey trueMonkey, falseMonkey;

    private final List<Long> items = new LinkedList<>();

    private long numberOfInspections;

    public Monkey(Function<Long, Long> worryLevelEvolvler, Predicate<Long> test, List<Long> items) {
        this.worryLevelEvolvler = worryLevelEvolvler;
        this.test = test;
        this.items.addAll(items);
    }

    public void setTargetMonkeys(Monkey trueMonkey, Monkey falseMonkey) {
        this.trueMonkey = trueMonkey;
        this.falseMonkey = falseMonkey;
    }

    void playRound(Function<Long, Long> reducer) {
        numberOfInspections += items.size();

        items.stream()
                .map(worryLevelEvolvler)
                .map(reducer)
                .forEach(this::throwItem);

        items.clear();
    }

    void throwItem(Long item) {
        if(test.test(item)) {
            trueMonkey.catchItem(item);
        } else {
            falseMonkey.catchItem(item);
        }
    }

    public void catchItem(Long item) {
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

