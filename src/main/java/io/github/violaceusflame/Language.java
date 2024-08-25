package io.github.violaceusflame;

import io.github.violaceusflame.validator.AbstractLetterValidator;
import io.github.violaceusflame.validator.EngLetterValidator;
import io.github.violaceusflame.validator.RusLetterValidator;

public enum Language {
    RUSSIAN(new RusLetterValidator()),
    ENGLISH(new EngLetterValidator());

    private final AbstractLetterValidator letterValidator;

    Language(AbstractLetterValidator letterValidator) {
        this.letterValidator = letterValidator;
    }

    public AbstractLetterValidator getLetterValidator() {
        return letterValidator;
    }
}
