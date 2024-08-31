package io.github.violaceusflame.dialog;

import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.mapper.MessageMapper;
import io.github.violaceusflame.validator.MinMaxValidator;

public abstract class MinMaxDialog extends AbstractDialog {
    public MinMaxDialog(Display display, String title, MessageMapper messageMapper, int min, int max) {
        super(display, title, new MinMaxValidator(min, max), messageMapper);
    }
}
