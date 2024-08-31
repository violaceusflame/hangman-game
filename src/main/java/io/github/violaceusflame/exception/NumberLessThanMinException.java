package io.github.violaceusflame.exception;

public class NumberLessThanMinException extends NumberValidationException {
    private static final String MESSAGE = "The number '%d' is less than the minimum number.";

    public NumberLessThanMinException(int invalidNumber) {
        super(String.format(MESSAGE, invalidNumber), invalidNumber);
    }
}
