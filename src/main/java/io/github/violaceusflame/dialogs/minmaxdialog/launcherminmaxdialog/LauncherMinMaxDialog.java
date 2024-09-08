package io.github.violaceusflame.dialogs.minmaxdialog.launcherminmaxdialog;

import io.github.violaceusflame.dialogs.common.Printer;
import io.github.violaceusflame.dialogs.dialogcenter.DialogCenter;
import io.github.violaceusflame.dialogs.minmaxdialog.MinMaxDialog;
import io.github.violaceusflame.messagecenter.MessageCenter;

public class LauncherMinMaxDialog extends MinMaxDialog {
    public LauncherMinMaxDialog(Printer infoPrinter, Printer errorPrinter, DialogCenter dialogCenter, String title, int max, int min) {
        super(infoPrinter, errorPrinter, title, new LauncherMinMaxMessageMapper(dialogCenter), min, max);
    }

    public LauncherMinMaxDialog(Printer printer, DialogCenter dialogCenter, String title, int max, int min) {
        super(printer, title, new LauncherMinMaxMessageMapper(dialogCenter), min, max);
    }
}
