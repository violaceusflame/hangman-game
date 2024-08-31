package io.github.violaceusflame.dialogs.letterdialog.en;

import io.github.violaceusflame.dialogs.common.AbstractDialog;
import io.github.violaceusflame.dialogs.common.printer.Printer;

public class EngLetterDialog extends AbstractDialog {
    public EngLetterDialog(Printer display, String title) {
        super(display, title, new EngLetterValidator(), new EngLetterMessageMapper());
    }
}
