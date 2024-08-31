package io.github.violaceusflame.dialog;

import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.mapper.RusLauncherMinMaxMessageMapper;

public class RusLauncherMinMaxDialog extends MinMaxDialog {
    public RusLauncherMinMaxDialog(Display display, String title, int min, int max) {
        super(display, title, new RusLauncherMinMaxMessageMapper(), min, max);
    }
}
