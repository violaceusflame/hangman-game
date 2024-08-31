package io.github.violaceusflame.dialogs.letterdialog.exception;

public class NotLetterException extends RuntimeException {
    private static final String NO_LETTER_MESSAGE = "This character is not letter";

    public NotLetterException() {
        super(NO_LETTER_MESSAGE);
    }
}
