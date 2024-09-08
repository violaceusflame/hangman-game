package io.github.violaceusflame.dialogs.letterdialog;

import io.github.violaceusflame.constant.Language;
import io.github.violaceusflame.dialogs.common.dialog.AbstractDialog;
import io.github.violaceusflame.dialogs.common.Printer;
import io.github.violaceusflame.dialogs.dialogcenter.DialogCenter;
import io.github.violaceusflame.dialogs.letterdialog.en.EnLetterValidator;
import io.github.violaceusflame.dialogs.letterdialog.pl.PlLetterValidator;
import io.github.violaceusflame.dialogs.letterdialog.ru.RuLetterValidator;
import io.github.violaceusflame.dialogs.letterdialog.ua.UaLetterValidator;
import io.github.violaceusflame.dialogs.common.Validator;

public class LetterDialog extends AbstractDialog {
    private static Validator<String> createValidator(Language language) {
        return switch (language) {
            case RU -> new RuLetterValidator();
            case EN -> new EnLetterValidator();
            case PL -> new PlLetterValidator();
            case UA -> new UaLetterValidator();
        };
    }

    public LetterDialog(Printer infoPrinter, Printer errorPrinter, DialogCenter dialogCenter, Language language, String title) {
        super(infoPrinter, errorPrinter, title, new LetterMessageMapper(dialogCenter), createValidator(language));
    }
}
