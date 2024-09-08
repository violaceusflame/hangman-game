package io.github.violaceusflame.dialogs.difficultdialog;

import io.github.violaceusflame.dialogs.common.messagemapper.AbstractOptionMessageMapper;
import io.github.violaceusflame.dialogs.dialogcenter.DialogCenter;

public class DifficultMessageMapper extends AbstractOptionMessageMapper {
    private enum Key {
        INVALID_DIFFICULT("invalid_difficult");

        public final String section = "DifficultMessageMapper";
        public final String key;

        Key(String key) {
            this.key = key;
        }
    }

    public DifficultMessageMapper(DialogCenter dialogCenter) {
        super(dialogCenter);
    }

    @Override
    protected String messageInputDoesNotMatchWithOptions() {
        return dialogCenter.get(Key.INVALID_DIFFICULT.section, Key.INVALID_DIFFICULT.key);
    }
}
