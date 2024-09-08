package io.github.violaceusflame.dialogs.languagedialog;

import io.github.violaceusflame.constant.Language;
import io.github.violaceusflame.dialogs.common.Printer;
import io.github.violaceusflame.dialogs.optiondialog.OptionDialog;

import java.util.Arrays;

public class LanguageDialog extends OptionDialog {
    private static final String[] LANGUAGES = Arrays.stream(Language.values()).map(Enum::name).toArray(String[]::new);

    public LanguageDialog(Printer infoPrinter, Printer errorPrinter, String title) {
        super(infoPrinter, errorPrinter, new LanguageMessageMapper(), title, LANGUAGES);
    }

    public LanguageDialog(Printer printer, String title) {
        super(printer, title, new LanguageMessageMapper(), LANGUAGES);
    }
}
