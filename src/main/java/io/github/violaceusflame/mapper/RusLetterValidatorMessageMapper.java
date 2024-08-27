package io.github.violaceusflame.mapper;

import io.github.violaceusflame.exception.MoreCharactersInputException;
import io.github.violaceusflame.exception.NotLetterException;
import io.github.violaceusflame.exception.NotLetterInLanguageException;

public class RusLetterValidatorMessageMapper implements MessageMapper {
    private static final String ALLOWED_ONLY_LETTERS_MESSAGE = "Вводить можно только буквы!";
    private static final String LETTER_NOT_CYRILLIC_MESSAGE = "Допустимы только буквы кириллицы!";
    private static final String MORE_LETTERS_MESSAGE = "Можно ввести только одну букву";

    @Override
    public String apply(RuntimeException e) {
        if (e instanceof NotLetterException) {
            return ALLOWED_ONLY_LETTERS_MESSAGE;
        } else if (e instanceof NotLetterInLanguageException) {
            return LETTER_NOT_CYRILLIC_MESSAGE;
        } else if (e instanceof MoreCharactersInputException) {
            return MORE_LETTERS_MESSAGE;
        }

        throw new IllegalArgumentException("Illegal exception: " + e);
    }
}
