package io.github.violaceusflame.dialogs.letterdialog.pl;

import io.github.violaceusflame.dialogs.common.AbstractLetterValidator;

import java.lang.Character.UnicodeBlock;

public class PlLetterValidator extends AbstractLetterValidator {
    public PlLetterValidator() {
        super(UnicodeBlock.BASIC_LATIN, UnicodeBlock.LATIN_EXTENDED_A, UnicodeBlock.LATIN_1_SUPPLEMENT);
    }
}
