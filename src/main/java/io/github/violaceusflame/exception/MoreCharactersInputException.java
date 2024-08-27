package io.github.violaceusflame.exception;

public class MoreCharactersInputException extends RuntimeException {
    private static final String MORE_CHARACTERS_MESSAGE = "Ввести можно только один символ!";

    public MoreCharactersInputException() {
        super(MORE_CHARACTERS_MESSAGE);
    }
}
