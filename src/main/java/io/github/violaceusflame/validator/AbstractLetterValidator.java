package io.github.violaceusflame.validator;

import io.github.violaceusflame.exception.MoreCharactersInputException;
import io.github.violaceusflame.exception.NotLetterException;
import io.github.violaceusflame.exception.NotLetterInLanguageException;

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
