package io.github.violaceusflame.mapper;

import io.github.violaceusflame.exception.MoreCharactersInputException;
import io.github.violaceusflame.exception.NotDigitException;
import io.github.violaceusflame.exception.NumberGreaterThanMaxException;
import io.github.violaceusflame.exception.NumberLessThanMinException;

public class RusLauncherMinMaxMessageMapper implements MessageMapper {
    private static final String ALLOWED_ONLY_ONE_CHARACTER_MESSAGE = "Ввести можно только один символ!";
    private static final String ALLOWED_ONLY_DIGIT = "Ввести можно только цифру!";
    private static final String ALLOWED_ONLY_MENU_NUMBER = "Неизвестная команда!";

    @Override
    public String apply(RuntimeException e) {
        if (e instanceof MoreCharactersInputException) {
            return ALLOWED_ONLY_ONE_CHARACTER_MESSAGE;
        }
        if (e instanceof NotDigitException) {
            return ALLOWED_ONLY_DIGIT;
        }
        if (e instanceof NumberLessThanMinException || e instanceof NumberGreaterThanMaxException) {
            return ALLOWED_ONLY_MENU_NUMBER;
        }

        throw new IllegalArgumentException("Illegal exception: " + e);
    }
}
