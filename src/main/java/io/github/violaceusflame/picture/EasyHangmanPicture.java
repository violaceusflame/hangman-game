package io.github.violaceusflame.picture;

public class EasyHangmanPicture extends HangmanPicture {
    public EasyHangmanPicture() {
        pictures.add("""
                 -----
                 |   |
                 |   O
                 |  /|\\
                 | _/ \\
                 |
                _|_
                """);
        pictures.add("""
                 -----
                 |   |
                 |   O
                 |  /|\\
                 | _/ \\_
                 |
                _|_
                """);
    }
}
