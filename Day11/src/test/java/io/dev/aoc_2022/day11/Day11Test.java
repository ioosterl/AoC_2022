package io.dev.aoc_2022.day11;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day11Test {

    @Test
    void partA() {

        assertThat(Day11.partA(getMonkeyList())).isEqualTo(10605);
    }

    @Test
    void partB() {

        assertThat(Day11.partB(getMonkeyList(), 23L * 19L * 13L * 17L)).isEqualTo(2713310158L);
    }

    private static List<Monkey> getMonkeyList() {
        Monkey m0 = new Monkey(i -> i * 19, i -> i % 23 == 0, List.of(79L, 98L));
        Monkey m1 = new Monkey(i -> i + 6, i -> i % 19 == 0, List.of(54L, 65L, 75L, 74L));
        Monkey m2 = new Monkey(i -> i * i, i -> i % 13 == 0, List.of(79L, 60L, 97L));
        Monkey m3 = new Monkey(i -> i + 3, i -> i % 17 == 0, List.of(74L));

        m0.setTargetMonkeys(m2, m3);
        m1.setTargetMonkeys(m2, m0);
        m2.setTargetMonkeys(m1, m3);
        m3.setTargetMonkeys(m0, m1);

        return List.of(m0, m1, m2, m3);
    }
}
