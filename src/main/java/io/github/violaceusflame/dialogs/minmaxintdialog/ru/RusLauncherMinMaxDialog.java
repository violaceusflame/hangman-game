package io.github.violaceusflame.dialogs.minmaxintdialog.ru;

import io.github.violaceusflame.dialogs.common.printer.Printer;
import io.github.violaceusflame.dialogs.minmaxintdialog.MinMaxDialog;

public class RusLauncherMinMaxDialog extends MinMaxDialog {
    public RusLauncherMinMaxDialog(Printer display, String title, int min, int max) {
        super(display, title, new RusLauncherMinMaxMessageMapper(), min, max);
    }
}
