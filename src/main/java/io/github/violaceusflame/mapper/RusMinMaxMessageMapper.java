package io.github.violaceusflame.mapper;

import io.github.violaceusflame.exception.MoreCharactersInputException;
import io.github.violaceusflame.exception.NotDigitException;
import io.github.violaceusflame.exception.NumberGreaterThanMaxException;
import io.github.violaceusflame.exception.NumberLessThanMinException;

public class RusMinMaxMessageMapper implements MessageMapper {
    // TODO: Проверка на один символ повторяется во многих местах. Подумать над сокращением этого куска кода.
    private static final String ALLOWED_ONLY_ONE_CHARACTER_MESSAGE = "Ввести можно только один символ!";
    private static final String ALLOWED_ONLY_DIGIT = "Это не число";
    private static final String NUMBER_LESS_THAN_MIN = "Число меньше минимального";
    private static final String NUMBER_LESS_THAN_MAX = "Число больше максимального";

    @Override
    public String apply(RuntimeException e) {
        if (e instanceof MoreCharactersInputException) {
            return ALLOWED_ONLY_ONE_CHARACTER_MESSAGE;
        }
        if (e instanceof NotDigitException) {
            return ALLOWED_ONLY_DIGIT;
        }
        if (e instanceof NumberLessThanMinException) {
            return NUMBER_LESS_THAN_MIN;
        }
        if (e instanceof NumberGreaterThanMaxException) {
            return NUMBER_LESS_THAN_MAX;
        }

        throw new IllegalArgumentException("Illegal exception: " + e);
    }
}
