package io.github.violaceusflame.dialogs.minmaxintdialog.exception;

public class NotDigitException extends RuntimeException {
    private static final String NO_DIGIT_MESSAGE = "Allowed only digits in input";

    public NotDigitException() {
        super(NO_DIGIT_MESSAGE);
    }
}
