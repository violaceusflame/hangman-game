package io.github.violaceusflame;

import io.github.violaceusflame.constant.Language;
import io.github.violaceusflame.dialogs.common.dialog.Dialog;
import io.github.violaceusflame.dialogs.common.Printer;
import io.github.violaceusflame.dialogs.dialogcenter.DialogCenter;
import io.github.violaceusflame.dialogs.dialogcenter.FileDialogCenter;
import io.github.violaceusflame.dialogs.dialogcenter.exception.UnableGetDialogCenterException;
import io.github.violaceusflame.dialogs.languagedialog.LanguageDialog;
import io.github.violaceusflame.display.ColorizedInfoDisplay;
import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.display.AnsiTextColor;
import io.github.violaceusflame.launcher.HangmanGameLauncher;
import io.github.violaceusflame.messagecenter.FileMessageCenter;
import io.github.violaceusflame.messagecenter.MessageCenter;
import io.github.violaceusflame.messagecenter.exception.UnableGetMessageCenterException;
import io.github.violaceusflame.repository.FileWordRepository;
import io.github.violaceusflame.repository.WordRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.StringJoiner;

public class MainColorizedTextConfig {
    private static final String UNABLE_GET_MESSAGE_CENTER = "Unable to get localization file";
    private static final String LEAVING_THE_GAME = "Leaving the game...";

    public static void main(String[] args) {
        Display infoDisplay = new ColorizedInfoDisplay(AnsiTextColor.CYAN);
        Display errorDisplay = new ColorizedInfoDisplay(AnsiTextColor.RED);
        Language language = inputLanguage(infoDisplay::show, errorDisplay::show);
        Optional<FileMessageCenter> messageCenterOptional = getMessageCenter("lang/%s", "%s_messages.ini", language, errorDisplay);
        if (messageCenterOptional.isEmpty()) {
            return;
        }
        Optional<FileDialogCenter> dialogCenterOptional = getDialogCenter("lang/%s", "%s_dialogs.ini", language, errorDisplay);
        if (dialogCenterOptional.isEmpty()) {
            return;
        }
        DialogCenter dialogCenter = dialogCenterOptional.get();
        MessageCenter messageCenter = messageCenterOptional.get();
        WordRepository wordRepository = new FileWordRepository("words", "words_%s.txt", language);
        HangmanGameLauncher hangmanGameLauncher = new HangmanGameLauncher(wordRepository, infoDisplay, errorDisplay, language, messageCenter, dialogCenter);
        hangmanGameLauncher.start();
    }

    private static Optional<FileMessageCenter> getMessageCenter(String directory, String filenameTemplate, Language language, Display display) {
        try {
            return Optional.of(new FileMessageCenter(directory, filenameTemplate, language));
        } catch (UnableGetMessageCenterException | UnableGetDialogCenterException e) {
            showErrorMessage(display);
            System.exit(1);
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
        display.show(UNABLE_GET_MESSAGE_CENTER);
        display.show(LEAVING_THE_GAME);
    }

    private static Language inputLanguage(Printer infoPrinter, Printer errorPrinter) {
        String languageMessage = getLanguageMessage();
        Dialog dialog = new LanguageDialog(infoPrinter, errorPrinter, languageMessage);
        String input = dialog.getInput();
        return Language.valueOf(input.toUpperCase());
    }

    private static String getLanguageMessage() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        Arrays.stream(Language.values()).map(Enum::name).forEach(stringJoiner::add);
        return "Choose language: %s".formatted(stringJoiner.toString());
    }
}
