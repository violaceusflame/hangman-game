package io.github.violaceusflame.constant;

import io.github.violaceusflame.dialogs.common.AbstractLetterValidator;
import io.github.violaceusflame.dialogs.letterdialog.en.EngLetterValidator;
import io.github.violaceusflame.dialogs.letterdialog.ru.RusLetterValidator;

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
