package io.github.violaceusflame;

import io.github.violaceusflame.dialogs.common.printer.Printer;
import io.github.violaceusflame.dialogs.common.printer.PrinterImpl;
import io.github.violaceusflame.dialogs.minmaxintdialog.MinMaxDialog;
import io.github.violaceusflame.dialogs.minmaxintdialog.ru.RusLauncherMinMaxDialog;
import io.github.violaceusflame.dialogs.letterdialog.ru.RusLetterDialog;
import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.display.InfoDisplay;
import io.github.violaceusflame.launcher.HangmanGameLauncher;
import io.github.violaceusflame.mapper.RusFileWordRepositoryMessageMapper;
import io.github.violaceusflame.mapper.MessageMapper;
import io.github.violaceusflame.repository.FileWordRepository;
import io.github.violaceusflame.repository.WordRepository;
import io.github.violaceusflame.constant.Language;
import io.github.violaceusflame.util.DialogPair;

public class Main {
    public static void main(String[] args) {
        WordRepository wordRepository = new FileWordRepository("words_ru.txt", Language.RUSSIAN);
        Display display = new InfoDisplay();
        Printer printer = new PrinterImpl(display);
        MinMaxDialog minMaxDialog = new RusLauncherMinMaxDialog(printer, "Ввод: ", 1, 2);
        RusLetterDialog rusLetterDialog = new RusLetterDialog(printer, "Ввод: ");
        MessageMapper messageMapper = new RusFileWordRepositoryMessageMapper();
        HangmanGameLauncher hangmanGameLauncher = new HangmanGameLauncher(wordRepository, DialogPair.of(minMaxDialog, rusLetterDialog), display, messageMapper);
        hangmanGameLauncher.start();
    }
}