package io.github.violaceusflame;

import io.github.violaceusflame.dialogs.common.printer.Printer;
import io.github.violaceusflame.dialogs.common.printer.PrinterImpl;
import io.github.violaceusflame.dialogs.letterdialog.en.EngLetterDialog;
import io.github.violaceusflame.dialogs.minmaxintdialog.MinMaxDialog;
import io.github.violaceusflame.dialogs.minmaxintdialog.ru.RusLauncherMinMaxDialog;
import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.display.InfoDisplay;
import io.github.violaceusflame.launcher.HangmanGameLauncher;
import io.github.violaceusflame.dialogs.letterdialog.en.EngLetterMessageMapper;
import io.github.violaceusflame.mapper.MessageMapper;
import io.github.violaceusflame.repository.FileWordRepository;
import io.github.violaceusflame.repository.WordRepository;
import io.github.violaceusflame.constant.Language;
import io.github.violaceusflame.util.DialogPair;

public class MainEngWordsConfig {
    public static void main(String[] args) {
        WordRepository wordRepository = new FileWordRepository("words_en.txt", Language.ENGLISH);
        Display display = new InfoDisplay();
        Printer printer = new PrinterImpl(display);
        MinMaxDialog minMaxDialog = new RusLauncherMinMaxDialog(printer, "Ввод: ", 1, 2);
        EngLetterDialog engLetterDialog = new EngLetterDialog(printer, "Ввод: ");
        MessageMapper messageMapper = new EngLetterMessageMapper();
        HangmanGameLauncher hangmanGameLauncher = new HangmanGameLauncher(wordRepository, DialogPair.of(minMaxDialog, engLetterDialog), display, messageMapper);
        hangmanGameLauncher.start();
    }
}
