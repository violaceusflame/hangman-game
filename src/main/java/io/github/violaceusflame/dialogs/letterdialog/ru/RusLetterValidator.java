package io.github.violaceusflame.dialogs.letterdialog.ru;

import io.github.violaceusflame.dialogs.common.AbstractLetterValidator;

public class RusLetterValidator extends AbstractLetterValidator {
    public RusLetterValidator() {
        super(Character.UnicodeBlock.CYRILLIC);
    }
}
