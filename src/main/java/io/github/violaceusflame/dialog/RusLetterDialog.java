package io.github.violaceusflame.dialog;

import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.mapper.RusLetterMessageMapper;
import io.github.violaceusflame.validator.RusLetterValidator;

public class RusLetterDialog extends AbstractDialog {
    public RusLetterDialog(Display display, String title) {
        super(display, title, new RusLetterValidator(), new RusLetterMessageMapper());
    }
}
