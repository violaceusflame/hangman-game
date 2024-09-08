package io.github.violaceusflame.dialogs.letterdialog;

import io.github.violaceusflame.dialogs.common.MoreCharactersInputException;
import io.github.violaceusflame.dialogs.common.messagemapper.AbstractMessageMapper;
import io.github.violaceusflame.dialogs.common.messagemapper.MessageMapper;
import io.github.violaceusflame.dialogs.dialogcenter.DialogCenter;
import io.github.violaceusflame.dialogs.letterdialog.exception.NotLetterException;
import io.github.violaceusflame.dialogs.letterdialog.exception.NotLetterInLanguageException;

public class LetterMessageMapper extends AbstractMessageMapper implements MessageMapper {
    private enum Key {
        ALLOWED_ONLY_LETTERS("allowed_only_letters"),
        LETTER_NOT_IN_LANGUAGE("letter_not_in_language"),
        MORE_LETTERS("more_letters");

        public final String section = "LetterMessageMapper";
        public final String key;

        Key(String key) {
            this.key = key;
        }
    }

    public LetterMessageMapper(DialogCenter dialogCenter) {
        super(dialogCenter);
    }

    @Override
    public String apply(RuntimeException e) {
        if (e instanceof NotLetterException) {
            return dialogCenter.get(Key.ALLOWED_ONLY_LETTERS.section, Key.ALLOWED_ONLY_LETTERS.key);
        } else if (e instanceof NotLetterInLanguageException) {
            return dialogCenter.get(Key.LETTER_NOT_IN_LANGUAGE.section, Key.LETTER_NOT_IN_LANGUAGE.key);
        } else if (e instanceof MoreCharactersInputException) {
            return dialogCenter.get(Key.MORE_LETTERS.section, Key.MORE_LETTERS.key);
        }

        throw new IllegalArgumentException("Illegal exception: " + e);
    }
}
