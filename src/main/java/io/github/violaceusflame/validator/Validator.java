package io.github.violaceusflame.validator;

public interface Validator<T> {
    void validate(T input);
}
