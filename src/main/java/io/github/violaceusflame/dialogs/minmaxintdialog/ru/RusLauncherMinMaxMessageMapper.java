package io.github.violaceusflame.dialogs.minmaxintdialog.ru;

import io.github.violaceusflame.dialogs.common.MoreCharactersInputException;
import io.github.violaceusflame.dialogs.minmaxintdialog.exception.NotDigitException;
import io.github.violaceusflame.dialogs.minmaxintdialog.exception.NumberGreaterThanMaxException;
import io.github.violaceusflame.dialogs.minmaxintdialog.exception.NumberLessThanMinException;
import io.github.violaceusflame.mapper.MessageMapper;

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
