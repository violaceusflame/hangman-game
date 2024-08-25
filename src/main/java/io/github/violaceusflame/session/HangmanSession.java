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
    private static final String NO_SUCH_LETTER_MESSAGE = "Ошибка! Буквы %s нет в загаданном слове";

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
        display.show(START_MESSAGE);
        display.show(hiddenWord.getMask());
        displayHangmanPicture();
    }

    public void start() {
        while (isSessionOn) {
            String letter = dialog.getInput();

            Optional<String> mask = getWordMaskWithRevealedLetter(letter);
            if (mask.isEmpty()) {
                continue;
            }
            display.show(mask.get());
            if (hiddenWord.isGuessed()) {
                endSession(Result.WIN);
            }
        }
    }

    private Optional<String> getWordMaskWithRevealedLetter(String letter) {
        try {
            return Optional.of(hiddenWord.revealLetter(letter));
        } catch (RuntimeException e) {
            handleException(e);
            return Optional.empty();
        }
    }

    private void handleException(RuntimeException e) {
        String message = convertExceptionToText(e);
        display.show(message);

        if (e instanceof NoSuchLetterException noSuchLetterException) {
            String wrongLetter = noSuchLetterException.getWrongLetter();
            handleWrongLetter(wrongLetter);
        }
    }

    private String convertExceptionToText(RuntimeException e) {
        if (e instanceof NoSuchLetterException noSuchLetterException) {
            String wrongLetter = noSuchLetterException.getWrongLetter();
            return String.format(NO_SUCH_LETTER_MESSAGE, wrongLetter);
        }
        throw new IllegalArgumentException("Unable convert exception to text: " + e);
    }

    private void handleWrongLetter(String letter) {
        letter = letter.toUpperCase();
        if (!wrongLetters.contains(letter)) {
            wrongLetters.add(letter);
        } else {
            display.show("Вы уже вводили эту букву, её нет в загаданном слове!");
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
        display.show(hiddenWord.getMask());
    }

    private void displayHangmanPicture() {
        String picture = hangmanPicture.get(difficult.MAX_ATTEMPTS - leftAttempts);
        display.show(picture);
    }

    private void displayWrongLetters() {
        display.show("Ошибки: " + getStringOfWrongLetters());
    }

    private String getStringOfWrongLetters() {
        StringBuilder letters = new StringBuilder();
        for (String letter : wrongLetters) {
            letters.append(letter).append(" ");
        }
        return letters.toString();
    }

    private void displayLeftAttempts() {
        display.show("Осталось попыток: " + leftAttempts);
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
        display.show(WIN);
    }

    private void displayLoseMessage() {
        display.show(ATTEMPTS_ARE_OVER);
        displayWrongLetters();
        String revealedWord = hiddenWord.reveal();
        display.show("Загаданное слово: " + revealedWord);
    }

    private boolean isLose(Result result) {
        return result == Result.LOSE;
    }

    private boolean isWin(Result result) {
        return result == Result.WIN;
    }
}
