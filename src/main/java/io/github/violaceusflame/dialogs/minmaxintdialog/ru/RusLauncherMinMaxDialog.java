package io.github.violaceusflame.dialogs.minmaxintdialog.ru;

import io.github.violaceusflame.dialogs.minmaxintdialog.MinMaxDialog;
import io.github.violaceusflame.display.Display;

public class RusLauncherMinMaxDialog extends MinMaxDialog {
    public RusLauncherMinMaxDialog(Display display, String title, int min, int max) {
        super(display, title, new RusLauncherMinMaxMessageMapper(), min, max);
    }
}
