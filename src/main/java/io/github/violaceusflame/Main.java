package io.github.violaceusflame;

import io.github.violaceusflame.dialog.Dialog;
import io.github.violaceusflame.dialog.LauncherDialog;
import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.display.InfoDisplay;
import io.github.violaceusflame.launcher.HangmanGameLauncher;
import io.github.violaceusflame.repository.FileWordRepository;
import io.github.violaceusflame.repository.WordRepository;

public class Main {
    public static void main(String[] args) {
        WordRepository fileWordRepository = new FileWordRepository("words.txt");
        Dialog launcherDialog = new LauncherDialog();
        Display infoDisplay = new InfoDisplay();
        HangmanGameLauncher hangmanGameLauncher = new HangmanGameLauncher(fileWordRepository, launcherDialog, infoDisplay);
        hangmanGameLauncher.start();
    }
}