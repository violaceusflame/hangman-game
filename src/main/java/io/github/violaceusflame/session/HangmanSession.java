package io.github.violaceusflame.session;

import io.github.violaceusflame.picture.EasyHangmanPicture;
import io.github.violaceusflame.picture.HangmanPicture;
import io.github.violaceusflame.dialog.Dialog;
import io.github.violaceusflame.dialog.HangmanSessionDialog;

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
    private static final String ATTEMPTS_ARE_OVER = "Все попытки закончились! Игра закончилась! Колобок повесился!";
    private static final String WIN = "Ура, победа!";
    private static final Dialog dialog = new HangmanSessionDialog();

    private final HiddenWord hiddenWord;
    private final Difficult difficult;
    private final HangmanPicture hangmanPicture;
    private final Set<String> wrongLetters = new HashSet<>();
    private int leftAttempts;
    private boolean isSessionOn = true;

    private enum Result {
        WIN, LOSE
    }

    public HangmanSession(Difficult difficult, HiddenWord hiddenWord) {
        this.hiddenWord = hiddenWord;
        this.difficult = difficult;
        this.leftAttempts = difficult.MAX_ATTEMPTS;
        this.hangmanPicture = difficult.hangmanPicture;
        displayStartMessage();
    }

    private void displayStartMessage() {
        dialog.display(START_MESSAGE);
        dialog.display(hiddenWord.getMask());
        displayHangmanPicture();
    }

    public void start() {
        while (isSessionOn) {
            String letter;
            try {
                letter = dialog.getInput();
            } catch (IllegalArgumentException e) {
                dialog.display(e.getMessage());
                continue;
            }

            try {
                String mask = hiddenWord.revealLetter(letter);
                dialog.display(mask);
                if (hiddenWord.isGuessed()) {
                    endSession(Result.WIN);
                }
            } catch (NoSuchElementException e) {
                handleWrongLetter(letter, e.getMessage());
            }
        }
    }

    private void handleWrongLetter(String letter, String exceptionMessage) {
        letter = letter.toUpperCase();
        if (!wrongLetters.contains(letter)) {
            dialog.display(exceptionMessage);
            wrongLetters.add(letter);
        } else {
            dialog.display("Вы уже вводили эту букву, её нет в загаданном слове!");
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
        dialog.display(hiddenWord.getMask());
    }

    private void displayHangmanPicture() {
        String picture = hangmanPicture.get(difficult.MAX_ATTEMPTS - leftAttempts);
        dialog.display(picture);
    }

    private void displayWrongLetters() {
        dialog.display("Ошибки: " + getStringOfWrongLetters());
    }

    private String getStringOfWrongLetters() {
        StringBuilder letters = new StringBuilder();
        for (String letter : wrongLetters) {
            letters.append(letter).append(" ");
        }
        return letters.toString();
    }

    private void displayLeftAttempts() {
        dialog.display("Осталось попыток: " + leftAttempts);
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
        dialog.display(WIN);
    }

    private void displayLoseMessage() {
        dialog.display(ATTEMPTS_ARE_OVER);
        displayWrongLetters();
        String revealedWord = hiddenWord.reveal();
        dialog.display("Загаданное слово: " + revealedWord);
    }

    private boolean isLose(Result result) {
        return result == Result.LOSE;
    }

    private boolean isWin(Result result) {
        return result == Result.WIN;
    }
}
