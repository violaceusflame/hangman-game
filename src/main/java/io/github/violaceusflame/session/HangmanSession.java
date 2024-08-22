package io.github.violaceusflame.session;

import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.exception.NoSuchLetterException;
import io.github.violaceusflame.picture.EasyHangmanPicture;
import io.github.violaceusflame.picture.HangmanPicture;
import io.github.violaceusflame.dialog.Dialog;

import java.util.*;

public class HangmanSession {
    public enum Difficult {
        EASY(8, new EasyHangmanPicture()), CLASSIC(6, new HangmanPicture());

        public final int MAX_ATTEMPTS;
        public final HangmanPicture hangmanPicture;

        Difficult(int maxAttempts, HangmanPicture hangmanPicture) {
            this.MAX_ATTEMPTS = maxAttempts;
            this.hangmanPicture = hangmanPicture;
        }
    }

    private static final String START_MESSAGE = "Игра началась!";
    private static final String ATTEMPTS_ARE_OVER = "Попыток больше нет! Игра закончилась! Колобок повесился!";
    private static final String WIN = "Ура, победа!";

    private final HiddenWord hiddenWord;
    private final Difficult difficult;
    private final Dialog dialog;
    private final Display display;
    private final HangmanPicture hangmanPicture;
    private final Set<String> wrongLetters = new HashSet<>();
    private int leftAttempts;
    private boolean isSessionOn = true;

    private enum Result {
        WIN, LOSE
    }

    public HangmanSession(Difficult difficult, HiddenWord hiddenWord, Dialog dialog, Display display) {
        this.hiddenWord = hiddenWord;
        this.difficult = difficult;
        this.dialog = dialog;
        this.display = display;
        this.leftAttempts = difficult.MAX_ATTEMPTS;
        this.hangmanPicture = difficult.hangmanPicture;
        displayStartMessage();
    }

    private void displayStartMessage() {
        display.display(START_MESSAGE);
        display.display(hiddenWord.getMask());
        displayHangmanPicture();
    }

    public void start() {
        while (isSessionOn) {
            String letter = getPlayerInput();

            Optional<String> mask = getWordMaskWithRevealedLetter(letter);
            if (mask.isEmpty()) {
                continue;
            }
            display.display(mask.get());
            if (hiddenWord.isGuessed()) {
                endSession(Result.WIN);
            }
        }
    }

    private Optional<String> getWordMaskWithRevealedLetter(String letter) {
        try {
            return Optional.of(hiddenWord.revealLetter(letter));
        } catch (NoSuchLetterException e) {
            handleWrongLetter(letter, e.getMessage());
            return Optional.empty();
        }
    }

    private String getPlayerInput() {
        while (true) {
            try {
                return dialog.getInput();
            } catch (IllegalArgumentException e) {
                display.display(e.getMessage());
            }
        }
    }

    private void handleWrongLetter(String letter, String exceptionMessage) {
        letter = letter.toUpperCase();
        if (!wrongLetters.contains(letter)) {
            display.display(exceptionMessage);
            wrongLetters.add(letter);
        } else {
            display.display("Вы уже вводили эту букву, её нет в загаданном слове!");
            displayErrorMessage();
            return;
        }

        updateLeftAttempts();
    }

    private void updateLeftAttempts() {
        --leftAttempts;
        if (leftAttempts == 0) {
            endSession(Result.LOSE);
        } else {
            displayErrorMessage();
        }
        displayHangmanPicture();
    }

    private void displayErrorMessage() {
        displayLeftAttempts();
        displayWrongLetters();
        display.display(hiddenWord.getMask());
    }

    private void displayHangmanPicture() {
        String picture = hangmanPicture.get(difficult.MAX_ATTEMPTS - leftAttempts);
        display.display(picture);
    }

    private void displayWrongLetters() {
        display.display("Ошибки: " + getStringOfWrongLetters());
    }

    private String getStringOfWrongLetters() {
        StringBuilder letters = new StringBuilder();
        for (String letter : wrongLetters) {
            letters.append(letter).append(" ");
        }
        return letters.toString();
    }

    private void displayLeftAttempts() {
        display.display("Осталось попыток: " + leftAttempts);
    }

    private void endSession(Result result) {
        if (isWin(result)) {
            displayWinMessage();
        } else if (isLose(result)) {
            displayLoseMessage();
        }
        isSessionOn = false;
    }

    private void displayWinMessage() {
        display.display(WIN);
    }

    private void displayLoseMessage() {
        display.display(ATTEMPTS_ARE_OVER);
        displayWrongLetters();
        String revealedWord = hiddenWord.reveal();
        display.display("Загаданное слово: " + revealedWord);
    }

    private boolean isLose(Result result) {
        return result == Result.LOSE;
    }

    private boolean isWin(Result result) {
        return result == Result.WIN;
    }
}
