package io.github.violaceusflame.dialogs.letterdialog.en;

import io.github.violaceusflame.dialogs.common.AbstractDialog;
import io.github.violaceusflame.display.Display;

public class EngLetterDialog extends AbstractDialog {
    public EngLetterDialog(Display display, String title) {
        super(display, title, new EngLetterValidator(), new EngLetterMessageMapper());
    }
}
