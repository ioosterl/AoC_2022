package io.dev.aoc_2022.day11;

import java.util.List;
import java.util.stream.IntStream;

public class Day11 {

    public static void main(String[] args) {
        List<Monkey> monkeys = buildMonkeyList();
        System.out.println(partA(monkeys));

    }

    public static long partA(List<Monkey> monkeys) {
        IntStream.range(0, 20)
                .forEach(i -> {
                    monkeys.forEach(Monkey::playRound);
                    printMonkeys(i, monkeys);
                });

        return monkeys.stream()
                .sorted(Monkey.compareByInspections.reversed())
                .limit(2)
                .map(Monkey::getNumberOfInspections)
                .reduce(1L, (i, j) -> i * j);
    }

    static void printMonkeys(int round, List<Monkey> monkeys) {
        System.out.println("After Round " + (round + 1));
        IntStream.range(0, monkeys.size())
                .forEach(i -> System.out.println("Monkey " + i + ":" + monkeys.get(i)));

    }


    private static List<Monkey> buildMonkeyList() {
        Monkey m0 = new Monkey(i -> i * 13, i -> i % 19 == 0, List.of(71, 86));
        Monkey m1 = new Monkey(i -> i + 3, i -> i % 2 == 0, List.of(66, 50, 90, 53, 88, 85));
        Monkey m2 = new Monkey(i -> i + 6, i -> i % 13 == 0, List.of(97, 54, 89, 62, 84, 80, 63));
        Monkey m3 = new Monkey(i -> i + 2, i -> i % 5 == 0, List.of(82, 97, 56, 92));
        Monkey m4 = new Monkey(i -> i * i, i -> i % 7 == 0, List.of(50, 99, 67, 61, 86));
        Monkey m5 = new Monkey(i -> i + 4, i -> i % 11 == 0, List.of(61, 66, 72, 55, 64, 53, 72, 63));
        Monkey m6 = new Monkey(i -> i * 7, i -> i % 17 == 0, List.of(59, 79, 63));
        Monkey m7 = new Monkey(i -> i + 7, i -> i % 3 == 0, List.of(55));

        m0.setTargetMonkeys(m6, m7);
        m1.setTargetMonkeys(m5, m4);
        m2.setTargetMonkeys(m4, m1);
        m3.setTargetMonkeys(m6, m0);
        m4.setTargetMonkeys(m5, m3);
        m5.setTargetMonkeys(m3, m0);
        m6.setTargetMonkeys(m2, m7);
        m7.setTargetMonkeys(m2, m1);

        return List.of(m0, m1, m2, m3, m4, m5, m6, m7);
    }
}
