package io.github.violaceusflame.exception;

public class NotLetterInLanguageException extends RuntimeException {
    private static final String NOT_LETTER_IN_LANGUAGE_MESSAGE = "Not a letter in language";

    public NotLetterInLanguageException() {
        super(NOT_LETTER_IN_LANGUAGE_MESSAGE);
    }
}
