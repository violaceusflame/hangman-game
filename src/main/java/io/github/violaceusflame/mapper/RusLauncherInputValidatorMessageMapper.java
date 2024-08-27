package io.github.violaceusflame.mapper;

import io.github.violaceusflame.exception.MoreCharactersInputException;
import io.github.violaceusflame.exception.NotDigitException;

public class RusLauncherInputValidatorMessageMapper implements MessageMapper {
    private static final String ALLOWED_ONLY_ONE_CHARACTER_MESSAGE = "Ввести можно только один символ!";
    private static final String ALLOWED_ONLY_DIGIT = "Ввести можно только цифру, соответствующую пункту меню!";

    @Override
    public String apply(RuntimeException e) {
        if (e instanceof MoreCharactersInputException) {
            return ALLOWED_ONLY_ONE_CHARACTER_MESSAGE;
        } else if (e instanceof NotDigitException) {
            return ALLOWED_ONLY_DIGIT;
        }

        throw new IllegalArgumentException("Illegal exception: " + e);
    }
}
