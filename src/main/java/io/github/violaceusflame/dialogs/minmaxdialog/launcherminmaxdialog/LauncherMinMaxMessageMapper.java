package io.github.violaceusflame.dialogs.minmaxdialog.launcherminmaxdialog;

import io.github.violaceusflame.dialogs.common.messagemapper.AbstractMinMaxMessageMapper;
import io.github.violaceusflame.dialogs.dialogcenter.DialogCenter;

public class LauncherMinMaxMessageMapper extends AbstractMinMaxMessageMapper {
    private enum Key {
        ALLOWED_ONLY_ONE_CHARACTER("allowed_only_one_character"),
        ALLOWED_ONLY_DIGIT("allowed_only_digit"),
        ALLOWED_ONLY_MENU_NUMBER("allowed_only_menu_number");

        public final String section = "LauncherMinMaxMessageMapper";
        public final String key;

        Key(String key) {
            this.key = key;
        }
    }

    public LauncherMinMaxMessageMapper(DialogCenter dialogCenter) {
        super(dialogCenter);
    }

    @Override
    protected String messageMoreCharactersInputException() {
        return dialogCenter.get(Key.ALLOWED_ONLY_ONE_CHARACTER.section, Key.ALLOWED_ONLY_ONE_CHARACTER.key);
    }

    @Override
    protected String messageNotDigitException() {
        return dialogCenter.get(Key.ALLOWED_ONLY_DIGIT.section, Key.ALLOWED_ONLY_DIGIT.key);
    }

    @Override
    protected String messageNumberLessThanMinException() {
        return dialogCenter.get(Key.ALLOWED_ONLY_MENU_NUMBER.section, Key.ALLOWED_ONLY_MENU_NUMBER.key);
    }

    @Override
    protected String messageNumberGreaterThanMaxException() {
        return messageNumberLessThanMinException();
    }
}
