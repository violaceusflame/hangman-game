package io.github.violaceusflame.dialogs.minmaxdialog;

import io.github.violaceusflame.dialogs.common.messagemapper.AbstractMinMaxMessageMapper;
import io.github.violaceusflame.dialogs.dialogcenter.DialogCenter;

public class MinMaxMessageMapper extends AbstractMinMaxMessageMapper {
    private enum Key {
        MORE_CHARACTERS("more_characters"),
        NOT_DIGIT("not_digit"),
        NUMBER_LESS_THAN_MIN("number_less_than_min"),
        NUMBER_GREATER_THAN_MAX("number_greater_than_max");

        public final String section = "MinMaxMessageMapper";
        public final String key;

        Key(String key) {
            this.key = key;
        }
    }

    public MinMaxMessageMapper(DialogCenter dialogCenter) {
        super(dialogCenter);
    }

    @Override
    protected String messageMoreCharactersInputException() {
        return dialogCenter.get(Key.MORE_CHARACTERS.section, Key.MORE_CHARACTERS.key);
    }

    @Override
    protected String messageNotDigitException() {
        return dialogCenter.get(Key.NOT_DIGIT.section, Key.NOT_DIGIT.key);
    }

    @Override
    protected String messageNumberLessThanMinException() {
        return dialogCenter.get(Key.NUMBER_LESS_THAN_MIN.section, Key.NUMBER_LESS_THAN_MIN.key);
    }

    @Override
    protected String messageNumberGreaterThanMaxException() {
        return dialogCenter.get(Key.NUMBER_GREATER_THAN_MAX.section, Key.NUMBER_GREATER_THAN_MAX.key);
    }
}
