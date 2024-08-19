package io.github.violaceusflame.dialog;

import io.github.violaceusflame.validator.LauncherInputValidator;

public class LauncherDialog extends AbstractDialog {
    public LauncherDialog() {
        super(new LauncherInputValidator());
    }
}
