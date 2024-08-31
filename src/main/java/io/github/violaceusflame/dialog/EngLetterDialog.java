package io.github.violaceusflame.dialog;

import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.mapper.EngLetterMessageMapper;
import io.github.violaceusflame.validator.EngLetterValidator;

public class EngLetterDialog extends AbstractDialog {
    public EngLetterDialog(Display display, String title) {
        super(display, title, new EngLetterValidator(), new EngLetterMessageMapper());
    }
}
