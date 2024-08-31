package io.github.violaceusflame;

import io.github.violaceusflame.dialog.MinMaxDialog;
import io.github.violaceusflame.dialog.RusLauncherMinMaxDialog;
import io.github.violaceusflame.dialog.RusLetterDialog;
import io.github.violaceusflame.display.ColorizedInfoDisplay;
import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.launcher.HangmanGameLauncher;
import io.github.violaceusflame.mapper.MessageMapper;
import io.github.violaceusflame.mapper.RusFileWordRepositoryMessageMapper;
import io.github.violaceusflame.repository.FileWordRepository;
import io.github.violaceusflame.repository.WordRepository;
import io.github.violaceusflame.constant.AnsiTextColor;
import io.github.violaceusflame.constant.Language;
import io.github.violaceusflame.util.DialogPair;

public class MainColorizedTextConfig {
    public static void main(String[] args) {
        WordRepository wordRepository = new FileWordRepository("words_ru.txt", Language.RUSSIAN);
        Display display = new ColorizedInfoDisplay(AnsiTextColor.CYAN);
        MinMaxDialog minMaxDialog = new RusLauncherMinMaxDialog(display, "Ввод: ", 1, 2);
        RusLetterDialog rusLetterDialog = new RusLetterDialog(display, "Ввод: ");
        MessageMapper messageMapper = new RusFileWordRepositoryMessageMapper();
        HangmanGameLauncher hangmanGameLauncher = new HangmanGameLauncher(wordRepository, DialogPair.of(minMaxDialog, rusLetterDialog), display, messageMapper);
        hangmanGameLauncher.start();
    }
}
