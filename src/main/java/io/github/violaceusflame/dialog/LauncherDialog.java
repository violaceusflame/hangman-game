package io.github.violaceusflame.dialog;

import io.github.violaceusflame.validator.dialog.LauncherInputValidator;

public class LauncherDialog extends AbstractDialog {
    public LauncherDialog() {
        super(new LauncherInputValidator());
    }
}
