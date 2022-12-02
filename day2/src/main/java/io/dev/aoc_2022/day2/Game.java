package io.dev.aoc_2022.day2;

import java.util.Map;

import static io.dev.aoc_2022.day2.Outcome.*;
import static io.dev.aoc_2022.day2.RockPaperScissor.*;

public class Game {

    private static final Map<RockPaperScissor, Integer> rpsPoints = Map.of(RockPaperScissor.ROCK, 1, PAPER, 2, RockPaperScissor.SCISSORS, 3);
    public static Outcome play(RockPaperScissor opponent, RockPaperScissor mine) {
        if (opponent == mine) {
            return DRAW;
        }

        if ((mine == RockPaperScissor.ROCK && opponent == RockPaperScissor.SCISSORS) ||
                (mine == RockPaperScissor.SCISSORS && opponent == PAPER) ||
                (mine == PAPER && opponent == RockPaperScissor.ROCK)) {
            return WIN;
        }

        return LOOSE;
    }

    public static int score(RockPaperScissor mine, Outcome outcome) {
        return rpsPoints.get(mine) + outcome.score;
    }

    public static RockPaperScissor winnincChoiceAgainst(RockPaperScissor opponent) {
        return switch (opponent) {
            case ROCK -> PAPER;
            case PAPER -> SCISSORS;
            case SCISSORS -> ROCK;
        };
    }

    public static RockPaperScissor loosingChoiceAgainst(RockPaperScissor opponent) {
        return switch (opponent) {
            case ROCK -> SCISSORS;
            case PAPER -> ROCK;
            case SCISSORS -> PAPER;
        };
    }
}
