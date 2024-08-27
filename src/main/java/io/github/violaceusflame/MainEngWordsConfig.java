package io.github.violaceusflame;

import io.github.violaceusflame.dialog.EngLetterDialog;
import io.github.violaceusflame.dialog.LauncherDialog;
import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.display.InfoDisplay;
import io.github.violaceusflame.launcher.HangmanGameLauncher;
import io.github.violaceusflame.mapper.EngLetterValidatorMessageMapper;
import io.github.violaceusflame.mapper.MessageMapper;
import io.github.violaceusflame.repository.FileWordRepository;
import io.github.violaceusflame.repository.WordRepository;
import io.github.violaceusflame.constant.Language;
import io.github.violaceusflame.util.DialogPair;

public class MainEngWordsConfig {
    public static void main(String[] args) {
        WordRepository wordRepository = new FileWordRepository("words_en.txt", Language.ENGLISH);
        Display display = new InfoDisplay();
        LauncherDialog launcherDialog = new LauncherDialog(display, "Ввод: ");
        EngLetterDialog engLetterDialog = new EngLetterDialog(display, "Ввод: ");
        MessageMapper messageMapper = new EngLetterValidatorMessageMapper();
        HangmanGameLauncher hangmanGameLauncher = new HangmanGameLauncher(wordRepository, DialogPair.of(launcherDialog, engLetterDialog), display, messageMapper);
        hangmanGameLauncher.start();
    }
}
