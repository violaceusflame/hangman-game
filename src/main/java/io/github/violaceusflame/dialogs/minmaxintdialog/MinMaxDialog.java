package io.github.violaceusflame.dialogs.minmaxintdialog;

import io.github.violaceusflame.dialogs.common.AbstractDialog;
import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.mapper.MessageMapper;

public abstract class MinMaxDialog extends AbstractDialog {
    public MinMaxDialog(Display display, String title, MessageMapper messageMapper, int min, int max) {
        super(display, title, new MinMaxValidator(min, max), messageMapper);
    }
}
