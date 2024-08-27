package io.github.violaceusflame;

import io.github.violaceusflame.dialog.Dialog;
import io.github.violaceusflame.dialog.LauncherDialog;
import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.display.InfoDisplay;
import io.github.violaceusflame.launcher.HangmanGameLauncher;
import io.github.violaceusflame.mapper.RusFileWordRepositoryMessageMapper;
import io.github.violaceusflame.mapper.MessageMapper;
import io.github.violaceusflame.repository.FileWordRepository;
import io.github.violaceusflame.repository.WordRepository;

public class Main {
    public static void main(String[] args) {
        WordRepository wordRepository = new FileWordRepository("words_ru.txt", Language.RUSSIAN);
        Display display = new InfoDisplay();
        Dialog dialog = new LauncherDialog(display, "Ввод: ");
        MessageMapper messageMapper = new RusFileWordRepositoryMessageMapper();
        HangmanGameLauncher hangmanGameLauncher = new HangmanGameLauncher(wordRepository, dialog, display, messageMapper);
        hangmanGameLauncher.start();
    }
}