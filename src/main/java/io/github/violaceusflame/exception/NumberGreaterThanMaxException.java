package io.github.violaceusflame.exception;

public class NumberGreaterThanMaxException extends NumberValidationException {
    private static final String MESSAGE = "The number '%d' is greater than the maximum number.";

    public NumberGreaterThanMaxException(int invalidNumber) {
        super(String.format(MESSAGE, invalidNumber), invalidNumber);
    }
}
