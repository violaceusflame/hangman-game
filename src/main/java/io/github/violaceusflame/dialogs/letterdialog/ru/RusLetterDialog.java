package io.github.violaceusflame.dialogs.letterdialog.ru;

import io.github.violaceusflame.dialogs.common.AbstractDialog;
import io.github.violaceusflame.dialogs.common.printer.Printer;

public class RusLetterDialog extends AbstractDialog {
    public RusLetterDialog(Printer display, String title) {
        super(display, title, new RusLetterValidator(), new RusLetterMessageMapper());
    }
}
