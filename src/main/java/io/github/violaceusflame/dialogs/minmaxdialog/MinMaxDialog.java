package io.github.violaceusflame.dialogs.minmaxdialog;

import io.github.violaceusflame.dialogs.common.Printer;
import io.github.violaceusflame.dialogs.common.dialog.AbstractDialog;
import io.github.violaceusflame.dialogs.common.messagemapper.MessageMapper;
import io.github.violaceusflame.dialogs.dialogcenter.DialogCenter;

public class MinMaxDialog extends AbstractDialog {
    public MinMaxDialog(Printer infoPrinter, Printer errorPrinter, DialogCenter dialogCenter, String title, int min, int max) {
        super(infoPrinter, errorPrinter, title, new MinMaxMessageMapper(dialogCenter), new MinMaxValidator(min, max));
    }

    public MinMaxDialog(Printer printer, DialogCenter dialogCenter, String title, int min, int max) {
        super(printer, title, new MinMaxMessageMapper(dialogCenter), new MinMaxValidator(min, max));
    }

    protected MinMaxDialog(Printer infoPrinter, Printer errorPrinter, String title, MessageMapper messageMapper, int min, int max) {
        super(infoPrinter, errorPrinter, title, messageMapper, new MinMaxValidator(min, max));
    }

    protected MinMaxDialog(Printer printer, String title, MessageMapper messageMapper, int min, int max) {
        super(printer, title, messageMapper, new MinMaxValidator(min, max));
    }
}
