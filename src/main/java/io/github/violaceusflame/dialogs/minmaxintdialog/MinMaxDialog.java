package io.github.violaceusflame.dialogs.minmaxintdialog;

import io.github.violaceusflame.dialogs.common.AbstractDialog;
import io.github.violaceusflame.dialogs.common.printer.Printer;
import io.github.violaceusflame.mapper.MessageMapper;

public abstract class MinMaxDialog extends AbstractDialog {
    public MinMaxDialog(Printer display, String title, MessageMapper messageMapper, int min, int max) {
        super(display, title, new MinMaxValidator(min, max), messageMapper);
    }
}
