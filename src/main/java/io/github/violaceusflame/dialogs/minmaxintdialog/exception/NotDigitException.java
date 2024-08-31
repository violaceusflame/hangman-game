package io.github.violaceusflame.dialogs.minmaxintdialog.exception;

public class NotDigitException extends RuntimeException {
    private static final String MESSAGE = "Allowed only digits in input";

    public NotDigitException() {
        super(MESSAGE);
    }
}
