package io.github.violaceusflame.dialogs.common.messagemapper;

import io.github.violaceusflame.dialogs.dialogcenter.DialogCenter;

public abstract class AbstractMessageMapper {
    protected final DialogCenter dialogCenter;

    public AbstractMessageMapper(DialogCenter dialogCenter) {
        this.dialogCenter = dialogCenter;
    }
}
