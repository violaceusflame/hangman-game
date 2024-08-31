package io.github.violaceusflame.dialogs.common;

import io.github.violaceusflame.dialogs.letterdialog.exception.NotLetterException;
import io.github.violaceusflame.dialogs.letterdialog.exception.NotLetterInLanguageException;
import io.github.violaceusflame.validator.Validator;

public abstract class AbstractLetterValidator implements Validator<String> {
    private final Character.UnicodeBlock unicodeBlock;

    public AbstractLetterValidator(Character.UnicodeBlock unicodeBlock) {
        this.unicodeBlock = unicodeBlock;
    }

    @Override
    public void validate(String playerInput) {
        if (playerInput.length() != 1) {
            throw new MoreCharactersInputException();
        }
        char typedLetter = playerInput.charAt(0);
        if (!isLetter(typedLetter)) {
            throw new NotLetterException();
        }
        if (!isLetterInLanguage(typedLetter)) {
            throw new NotLetterInLanguageException();
        }
    }

    public boolean isLetterInLanguage(char letter) {
        return isLetter(letter) && Character.UnicodeBlock.of(letter).equals(unicodeBlock);
    }

    public boolean isLetter(char letter) {
        return Character.isLetter(letter);
    }
}
