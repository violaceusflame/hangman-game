package io.github.violaceusflame.dialogs.letterdialog.en;

import io.github.violaceusflame.dialogs.common.MoreCharactersInputException;
import io.github.violaceusflame.dialogs.letterdialog.exception.NotLetterException;
import io.github.violaceusflame.dialogs.letterdialog.exception.NotLetterInLanguageException;
import io.github.violaceusflame.mapper.MessageMapper;

public class EngLetterMessageMapper implements MessageMapper {
    private static final String ALLOWED_ONLY_LETTERS_MESSAGE = "Вводить можно только буквы!";
    private static final String LETTER_NOT_LATIN_MESSAGE = "Допустимы только буквы латиницы!";
    private static final String MORE_LETTERS_MESSAGE = "Можно ввести только одну букву";

    @Override
    public String apply(RuntimeException e) {
        if (e instanceof NotLetterException) {
            return ALLOWED_ONLY_LETTERS_MESSAGE;
        } else if (e instanceof NotLetterInLanguageException) {
            return LETTER_NOT_LATIN_MESSAGE;
        } else if (e instanceof MoreCharactersInputException) {
            return MORE_LETTERS_MESSAGE;
        }

        throw new IllegalArgumentException("Illegal exception" + e);
    }
}
