package io.github.violaceusflame.picture;

import java.util.ArrayList;
import java.util.List;

public class HangmanPicture {
    protected final List<String> pictures = new ArrayList<>();

    public HangmanPicture() {
        pictures.add("""
                 -----
                 |   |
                 |
                 |
                 |
                 |
                _|_
                """);
        pictures.add("""
                 -----
                 |   |
                 |   O
                 |
                 |
                 |
                _|_
                """);
        pictures.add("""
                 -----
                 |   |
                 |   O
                 |   |
                 |
                 |
                _|_
                """);
        pictures.add("""
                 -----
                 |   |
                 |   O
                 |  /|
                 |
                 |
                _|_
                """);
        pictures.add("""
                 -----
                 |   |
                 |   O
                 |  /|\\
                 |
                 |
                _|_
                """);
        pictures.add("""
                 -----
                 |   |
                 |   O
                 |  /|\\
                 |  /
                 |
                _|_
                """);
        pictures.add("""
                 -----
                 |   |
                 |   O
                 |  /|\\
                 |  / \\
                 |
                _|_
                """);
    }

    public String get(int attemptNumber) {
        return pictures.get(attemptNumber);
    }
}
