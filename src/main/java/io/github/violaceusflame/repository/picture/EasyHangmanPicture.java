package io.github.violaceusflame.repository.picture;

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
