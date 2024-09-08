package io.github.violaceusflame.dialogs.common.messagemapper;

import io.github.violaceusflame.dialogs.common.MoreCharactersInputException;
import io.github.violaceusflame.dialogs.dialogcenter.DialogCenter;
import io.github.violaceusflame.dialogs.minmaxdialog.exception.NotDigitException;
import io.github.violaceusflame.dialogs.minmaxdialog.exception.NumberGreaterThanMaxException;
import io.github.violaceusflame.dialogs.minmaxdialog.exception.NumberLessThanMinException;

public abstract class AbstractMinMaxMessageMapper extends AbstractMessageMapper implements MessageMapper {
    public AbstractMinMaxMessageMapper(DialogCenter dialogCenter) {
        super(dialogCenter);
    }

    @Override
    public String apply(RuntimeException e) {
        if (e instanceof MoreCharactersInputException) {
            return messageMoreCharactersInputException();
        } else if (e instanceof NotDigitException) {
            return messageNotDigitException();
        } else if (e instanceof NumberLessThanMinException) {
            return messageNumberLessThanMinException();
        } else if (e instanceof NumberGreaterThanMaxException) {
            return messageNumberGreaterThanMaxException();
        }

        throw new IllegalArgumentException("Illegal exception: " + e);
    }

    protected abstract String messageMoreCharactersInputException();
    protected abstract String messageNotDigitException();
    protected abstract String messageNumberLessThanMinException();
    protected abstract String messageNumberGreaterThanMaxException();
}
