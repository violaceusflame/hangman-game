package io.github.violaceusflame.dialog;

import io.github.violaceusflame.validator.HangmanSessionInputValidator;

public class HangmanSessionDialog extends AbstractDialog {
    public HangmanSessionDialog() {
        super(new HangmanSessionInputValidator());
    }
}
