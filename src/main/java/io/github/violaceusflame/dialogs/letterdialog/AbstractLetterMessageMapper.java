package io.github.violaceusflame.dialogs.letterdialog;

import io.github.violaceusflame.dialogs.common.MoreCharactersInputException;
import io.github.violaceusflame.dialogs.letterdialog.exception.NotLetterException;
import io.github.violaceusflame.dialogs.letterdialog.exception.NotLetterInLanguageException;
import io.github.violaceusflame.mapper.MessageMapper;

public abstract class AbstractLetterMessageMapper implements MessageMapper {
    @Override
    public String apply(RuntimeException e) {
        if (e instanceof NotLetterException) {
            return messageAllowedOnlyLetters();
        } else if (e instanceof NotLetterInLanguageException) {
            return messageLetterNotInLanguage();
        } else if (e instanceof MoreCharactersInputException) {
            return messageMoreLetters();
        }

        throw new IllegalArgumentException("Illegal exception: " + e);
    }

    protected abstract String messageAllowedOnlyLetters();
    protected abstract String messageLetterNotInLanguage();
    protected abstract String messageMoreLetters();
}
