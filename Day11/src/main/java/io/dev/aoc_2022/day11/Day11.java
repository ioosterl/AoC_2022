package io.dev.aoc_2022.day11;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Day11 {

    public static void main(String[] args) {
        System.out.println("Part A: " + partA(buildMonkeyList()));
        System.out.println("Part B: " + partB(buildMonkeyList(), getSuperMod()));

    }

    private static long getSuperMod() {
        //redacted (depending on input file)
        return 1L;
    }

    public static long partB(List<Monkey> monkeys, final long superMod) {
        int numberOfRounds = 10_000;
        Function<Long, Long> reducer = i -> i%superMod;
        return observe(monkeys, numberOfRounds, reducer);
    }

    private static Long observe(List<Monkey> monkeys, int numberOfRounds, Function<Long, Long> reducer) {
        IntStream.range(0, numberOfRounds)
                .forEach(i -> monkeys.forEach(m -> m.playRound(reducer)));

        return monkeys.stream()
                .sorted(Monkey.compareByInspections.reversed())
                .limit(2)
                .map(Monkey::getNumberOfInspections)
                .reduce(1L, (i, j) -> i * j);
    }

    public static long partA(List<Monkey> monkeys) {
        return observe(monkeys, 20 , i -> i/3);
    }

    private static List<Monkey> buildMonkeyList() {
        //redacted, based on input file
        return Collections.emptyList();
    }
}
