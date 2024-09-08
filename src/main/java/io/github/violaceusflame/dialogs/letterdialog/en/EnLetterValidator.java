package io.github.violaceusflame.dialogs.letterdialog.en;

import io.github.violaceusflame.dialogs.common.AbstractLetterValidator;

import java.lang.Character.UnicodeBlock;

public class EnLetterValidator extends AbstractLetterValidator {
    public EnLetterValidator() {
        super(UnicodeBlock.BASIC_LATIN);
    }
}
