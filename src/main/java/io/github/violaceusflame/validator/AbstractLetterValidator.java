package io.github.violaceusflame.validator;

import io.github.violaceusflame.exception.ValidatorException;

public abstract class AbstractLetterValidator implements Validator<String> {
    private final Character.UnicodeBlock language;

    public AbstractLetterValidator(Character.UnicodeBlock language) {
        this.language = language;
    }

    @Override
    public void validate(String playerInput) {
        char typedLetter = playerInput.charAt(0);
        if (playerInput.length() != 1) {
            throw new ValidatorException(Result.MORE_LETTERS);
        }
        if (!isLetter(typedLetter)) {
            throw new ValidatorException(Result.ALLOWED_ONLY_LETTERS);
        }
        if (!isLetterInLanguage(typedLetter)) {
            throw new ValidatorException(Result.LETTER_NOT_IN_LANGUAGE);
        }
    }

    public boolean isLetterInLanguage(char letter) {
        return isLetter(letter) && Character.UnicodeBlock.of(letter).equals(language);
    }

    public boolean isLetter(char letter) {
        return Character.isLetter(letter);
    }

    public enum Result {
        ALLOWED_ONLY_LETTERS,
        LETTER_NOT_IN_LANGUAGE,
        MORE_LETTERS
    }
}
