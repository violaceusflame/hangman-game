package io.github.violaceusflame;

import io.github.violaceusflame.dialogs.common.dialog.Dialog;
import io.github.violaceusflame.dialogs.common.Printer;
import io.github.violaceusflame.dialogs.dialogcenter.DialogCenter;
import io.github.violaceusflame.dialogs.dialogcenter.FileDialogCenter;
import io.github.violaceusflame.dialogs.dialogcenter.exception.UnableGetDialogCenterException;
import io.github.violaceusflame.dialogs.languagedialog.LanguageDialog;
import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.display.InfoDisplay;
import io.github.violaceusflame.messagecenter.exception.UnableGetMessageCenterException;
import io.github.violaceusflame.launcher.HangmanGameLauncher;
import io.github.violaceusflame.messagecenter.FileMessageCenter;
import io.github.violaceusflame.messagecenter.MessageCenter;
import io.github.violaceusflame.repository.FileWordRepository;
import io.github.violaceusflame.repository.WordRepository;
import io.github.violaceusflame.constant.Language;

import java.util.Arrays;
import java.util.Optional;
import java.util.StringJoiner;

public class Main {
    private static final String UNABLE_GET_LOCALIZATION_FILE = "Unable to get localization file";
    private static final String LEAVING_THE_GAME = "Leaving the game...";

    public static void main(String[] args) {
        Display display = new InfoDisplay();
        Language language = inputLanguage(display::show);
        Optional<FileMessageCenter> messageCenterOptional = getMessageCenter("lang/%s", "%s_messages.ini", language, display);
        if (messageCenterOptional.isEmpty()) {
            return;
        }
        Optional<FileDialogCenter> dialogCenterOptional = getDialogCenter("lang/%s", "%s_dialogs.ini", language, display);
        if (dialogCenterOptional.isEmpty()) {
            return;
        }
        DialogCenter dialogCenter = dialogCenterOptional.get();
        MessageCenter messageCenter = messageCenterOptional.get();
        WordRepository wordRepository = new FileWordRepository("words", "words_%s.txt", language);
        HangmanGameLauncher hangmanGameLauncher = new HangmanGameLauncher(wordRepository, display, language, messageCenter, dialogCenter);
        hangmanGameLauncher.start();
    }

    private static Optional<FileMessageCenter> getMessageCenter(String directory, String filenameTemplate, Language language, Display display) {
        try {
            return Optional.of(new FileMessageCenter(directory, filenameTemplate, language));
        } catch (UnableGetMessageCenterException e) {
            showErrorMessage(display);
            System.exit(0);
            return Optional.empty();
        }
    }

    private static Optional<FileDialogCenter> getDialogCenter(String directory, String filenameTemplate, Language language, Display display) {
        try {
            return Optional.of(new FileDialogCenter(directory, filenameTemplate, language));
        } catch (UnableGetDialogCenterException e) {
            showErrorMessage(display);
            System.exit(0);
            return Optional.empty();
        }
    }

    private static void showErrorMessage(Display display) {
        display.show(UNABLE_GET_LOCALIZATION_FILE);
        display.show(LEAVING_THE_GAME);
    }

    private static Language inputLanguage(Printer printer) {
        String languageMessage = getLanguageMessage();
        Dialog dialog = new LanguageDialog(printer, languageMessage);
        String input = dialog.getInput();
        return Language.valueOf(input.toUpperCase());
    }

    private static String getLanguageMessage() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        Arrays.stream(Language.values()).map(Enum::name).forEach(stringJoiner::add);
        return "Choose language: %s".formatted(stringJoiner.toString());
    }
}