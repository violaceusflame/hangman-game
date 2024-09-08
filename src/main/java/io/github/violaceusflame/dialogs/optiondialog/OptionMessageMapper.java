package io.github.violaceusflame.dialogs.optiondialog;

import io.github.violaceusflame.dialogs.common.messagemapper.AbstractOptionMessageMapper;
import io.github.violaceusflame.dialogs.dialogcenter.DialogCenter;

public class OptionMessageMapper extends AbstractOptionMessageMapper {
    private enum Key {
        INPUT_DOES_NOT_MATCH_WITH_OPTIONS("input_does_not_match_with_options");

        public final String section = "OptionMessageMapper";
        public final String key;

        Key(String key) {
            this.key = key;
        }
    }

    public OptionMessageMapper(DialogCenter dialogCenter) {
        super(dialogCenter);
    }

    @Override
    protected String messageInputDoesNotMatchWithOptions() {
        return dialogCenter.get(Key.INPUT_DOES_NOT_MATCH_WITH_OPTIONS.section, Key.INPUT_DOES_NOT_MATCH_WITH_OPTIONS.key);
    }
}
