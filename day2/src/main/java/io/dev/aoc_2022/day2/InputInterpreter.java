package io.dev.aoc_2022.day2;

public class InputInterpreter {
    public static RockPaperScissor interpretOpponent(String a) {
        return switch(a.toUpperCase().trim()) {
            case "A" -> RockPaperScissor.ROCK;
            case "B" -> RockPaperScissor.PAPER;
            case "C" -> RockPaperScissor.SCISSORS;
            default -> throw new IllegalArgumentException("Unknown input for other");
        };
    }

    public static RockPaperScissor interpretMine(String a) {
        return switch(a.toUpperCase().trim()) {
            case "X" -> RockPaperScissor.ROCK;
            case "Y" -> RockPaperScissor.PAPER;
            case "Z" -> RockPaperScissor.SCISSORS;
            default -> throw new IllegalArgumentException("Unknown input for other");
        };
    }

    public static RockPaperScissor determineMine(RockPaperScissor opponent, String desiredOutcome) {
        return switch (desiredOutcome.toUpperCase().trim()) {
            case "X" -> Game.loosingChoiceAgainst(opponent);
            case "Y" -> opponent;
            case "Z" -> Game.winnincChoiceAgainst(opponent);
            default -> throw new IllegalArgumentException("Unknown input for desired outcome");
        };
    }
}
