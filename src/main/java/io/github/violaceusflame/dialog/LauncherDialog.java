package io.github.violaceusflame.dialog;

import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.mapper.LauncherInputValidatorMessageMapper;
import io.github.violaceusflame.validator.LauncherInputValidator;

public class LauncherDialog extends AbstractDialog {
    public LauncherDialog(Display display, String title) {
        super(display, title, new LauncherInputValidator(), new LauncherInputValidatorMessageMapper());
    }
}
