package io.github.violaceusflame.dialogs.letterdialog.ru;

import io.github.violaceusflame.dialogs.common.AbstractLetterValidator;

import java.lang.Character.UnicodeBlock;

public class RuLetterValidator extends AbstractLetterValidator {
    public RuLetterValidator() {
        super(UnicodeBlock.CYRILLIC);
    }
}
