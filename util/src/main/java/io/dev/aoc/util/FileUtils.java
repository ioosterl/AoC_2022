package io.dev.aoc.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class FileUtils {

    public static <R> R processLinesAsStream(String filenameOnClasspath, Function<Stream<String>, R> linesProcessor) {
        try (Stream<String> lines =
                     Files.lines(
                             getNullSafePath(filenameOnClasspath))) {
            return linesProcessor.apply(lines);

        }catch (IOException e) {
            throw new IllegalArgumentException("Could not process file " + filenameOnClasspath, e);
        }
    }

    public static void consumeLinesFromStream(String filenameOnClasspath, Consumer<String> lineConsumer) {
        try (Stream<String> lines =
                     Files.lines(
                             getNullSafePath(filenameOnClasspath))) {
            lines.forEach(lineConsumer);

        } catch (IOException e) {
            throw new IllegalArgumentException("Could not process file " + filenameOnClasspath, e);
        }
    }

    public static <R> R processLinesAsList(String filenameOnClasspath, Function<List<String>, R> linesProcessor) {
        try {
            List<String> lines = Files.readAllLines(getNullSafePath(filenameOnClasspath));
            return linesProcessor.apply(lines);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not process file " + filenameOnClasspath, e);
        }
    }

    public static Path getNullSafePath(String filename) {
        try {
            return Paths.get(
                    Objects.requireNonNull(
                            FileUtils.class.getClassLoader().getResource(filename)).toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Could not find file " + filename, e);
        }
    }
}
