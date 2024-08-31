package io.github.violaceusflame.dialog;

import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.mapper.RusLauncherMinMaxValidatorMessageMapper;

public class RusLauncherMinMaxDialog extends MinMaxDialog {
    public RusLauncherMinMaxDialog(Display display, String title, int min, int max) {
        super(display, title, new RusLauncherMinMaxValidatorMessageMapper(), min, max);
    }
}
