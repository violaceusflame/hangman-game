package io.github.violaceusflame.exception;

public class ValidatorException extends RuntimeException {
    private final Object value;

    public ValidatorException(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
