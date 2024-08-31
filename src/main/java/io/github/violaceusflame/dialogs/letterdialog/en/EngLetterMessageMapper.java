package io.github.violaceusflame.dialogs.letterdialog.en;

import io.github.violaceusflame.dialogs.letterdialog.AbstractLetterMessageMapper;

public class EngLetterMessageMapper extends AbstractLetterMessageMapper {
    private static final String ALLOWED_ONLY_LETTERS_MESSAGE = "Вводить можно только буквы!";
    private static final String LETTER_NOT_LATIN_MESSAGE = "Допустимы только буквы латиницы!";
    private static final String MORE_LETTERS_MESSAGE = "Можно ввести только одну букву";

    @Override
    protected String messageAllowedOnlyLetters() {
        return ALLOWED_ONLY_LETTERS_MESSAGE;
    }

    @Override
    protected String messageLetterNotInLanguage() {
        return LETTER_NOT_LATIN_MESSAGE;
    }

    @Override
    protected String messageMoreLetters() {
        return MORE_LETTERS_MESSAGE;
    }
}
