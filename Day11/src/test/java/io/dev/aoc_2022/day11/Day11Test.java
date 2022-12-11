package io.dev.aoc_2022.day11;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day11Test {

    @Test
    void partA() {
        Monkey m0 = new Monkey(i -> i * 19, i -> i % 23 == 0, List.of(79, 98));
        Monkey m1 = new Monkey(i -> i + 6, i -> i % 19 == 0, List.of(54, 65, 75, 74));
        Monkey m2 = new Monkey(i -> i * i, i -> i % 13 == 0, List.of(79, 60, 97));
        Monkey m3 = new Monkey(i -> i + 3, i -> i % 17 == 0, List.of(74));

        m0.setTargetMonkeys(m2,m3);
        m1.setTargetMonkeys(m2,m0);
        m2.setTargetMonkeys(m1,m3);
        m3.setTargetMonkeys(m0,m1);


        assertThat(Day11.partA(List.of(m0,m1,m2,m3))).isEqualTo(10605);
    }
}
