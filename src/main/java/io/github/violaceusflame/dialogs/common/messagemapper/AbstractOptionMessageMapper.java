package io.github.violaceusflame.dialogs.common.messagemapper;

import io.github.violaceusflame.dialogs.dialogcenter.DialogCenter;
import io.github.violaceusflame.dialogs.optiondialog.exception.InputDoesNotMatchWithOptionsException;

public abstract class AbstractOptionMessageMapper extends AbstractMessageMapper implements MessageMapper {
    public AbstractOptionMessageMapper(DialogCenter dialogCenter) {
        super(dialogCenter);
    }

    @Override
    public String apply(RuntimeException e) {
        if (e instanceof InputDoesNotMatchWithOptionsException) {
            return messageInputDoesNotMatchWithOptions();
        }

        throw new IllegalArgumentException("Illegal exception: " + e);
    }

    protected abstract String messageInputDoesNotMatchWithOptions();
}
