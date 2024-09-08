package io.github.violaceusflame.session;

import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.exception.NoSuchLetterException;
import io.github.violaceusflame.messagecenter.MessageCenter;
import io.github.violaceusflame.picture.EasyHangmanPicture;
import io.github.violaceusflame.picture.HangmanPicture;
import io.github.violaceusflame.dialogs.common.dialog.Dialog;

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

    private final HiddenWord hiddenWord;
    private final Difficult difficult;
    private final Dialog dialog;
    private final Display infoDisplay;
    private final Display errorDisplay;
    private final MessageCenter messageCenter;
    private final HangmanPicture hangmanPicture;
    private final Set<String> wrongLetters = new HashSet<>();
    private int leftAttempts;
    private boolean isSessionOn = true;

    private enum Result {
        WIN, LOSE
    }

    private enum Key {
        START("start"),
        ATTEMPTS_ARE_OVER("attempts_are_over"),
        WIN("win"),
        NO_SUCH_LETTER_TEMPLATE("no_such_letter_template"),
        LETTER_ALREADY_ENTERED("letter_already_entered"),
        ERRORS("errors"),
        LEFT_ATTEMPTS("left_attempts"),
        HIDDEN_WORD("hidden_word");

        public final String section = "HangmanSession";
        public final String key;

        Key(String key) {
            this.key = key;
        }
    }

    public HangmanSession(Difficult difficult, HiddenWord hiddenWord, Dialog dialog, Display infoDisplay, Display errorDisplay, MessageCenter messageCenter) {
        this.hiddenWord = hiddenWord;
        this.difficult = difficult;
        this.dialog = dialog;
        this.infoDisplay = infoDisplay;
        this.errorDisplay = errorDisplay;
        this.messageCenter = messageCenter;
        this.leftAttempts = difficult.MAX_ATTEMPTS;
        this.hangmanPicture = difficult.hangmanPicture;
        displayStartMessage();
    }

    public HangmanSession(Difficult difficult, HiddenWord hiddenWord, Dialog dialog, Display display, MessageCenter messageCenter) {
        this(difficult, hiddenWord, dialog, display, display, messageCenter);
    }

    private void displayStartMessage() {
        infoDisplay.show(messageCenter.get(Key.START.section, Key.START.key));
        infoDisplay.show(hiddenWord.getMask());
        displayHangmanPicture();
    }

    public void start() {
        while (isSessionOn) {
            String letter = dialog.getInput();

            Optional<String> mask = getWordMaskWithRevealedLetter(letter);
            if (mask.isEmpty()) {
                continue;
            }
            infoDisplay.show(mask.get());
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
        errorDisplay.show(message);

        if (e instanceof NoSuchLetterException noSuchLetterException) {
            String wrongLetter = noSuchLetterException.getWrongLetter();
            handleWrongLetter(wrongLetter);
        }
    }

    private String convertExceptionToText(RuntimeException e) {
        if (e instanceof NoSuchLetterException noSuchLetterException) {
            String wrongLetter = noSuchLetterException.getWrongLetter();
            String noSuchLetterTemplate = messageCenter.get(Key.NO_SUCH_LETTER_TEMPLATE.section, Key.NO_SUCH_LETTER_TEMPLATE.key);
            return noSuchLetterTemplate.formatted(wrongLetter);
        }
        throw new IllegalArgumentException("Unable convert exception to text: " + e);
    }

    private void handleWrongLetter(String letter) {
        letter = letter.toUpperCase();
        if (!wrongLetters.contains(letter)) {
            wrongLetters.add(letter);
        } else {
            errorDisplay.show(messageCenter.get(Key.LETTER_ALREADY_ENTERED.section, Key.LETTER_ALREADY_ENTERED.key));
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
        infoDisplay.show(hiddenWord.getMask());
    }

    private void displayHangmanPicture() {
        String picture = hangmanPicture.get(difficult.MAX_ATTEMPTS - leftAttempts);
        infoDisplay.show(picture);
    }

    private void displayWrongLetters() {
        String errorsMessage = messageCenter.get(Key.ERRORS.section, Key.ERRORS.key);
        infoDisplay.show(errorsMessage + getStringOfWrongLetters());
    }

    private String getStringOfWrongLetters() {
        StringBuilder letters = new StringBuilder();
        for (String letter : wrongLetters) {
            letters.append(letter).append(" ");
        }
        return letters.toString();
    }

    private void displayLeftAttempts() {
        String leftAttemptsMessage = messageCenter.get(Key.LEFT_ATTEMPTS.section, Key.LEFT_ATTEMPTS.key);
        infoDisplay.show(leftAttemptsMessage + leftAttempts);
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
        infoDisplay.show(messageCenter.get(Key.WIN.section, Key.WIN.key));
    }

    private void displayLoseMessage() {
        errorDisplay.show(messageCenter.get(Key.ATTEMPTS_ARE_OVER.section, Key.ATTEMPTS_ARE_OVER.key));
        displayWrongLetters();
        String revealedWord = hiddenWord.reveal();
        String hiddenWordMessage = messageCenter.get(Key.HIDDEN_WORD.section, Key.HIDDEN_WORD.key);
        infoDisplay.show(hiddenWordMessage + revealedWord);
    }

    private boolean isLose(Result result) {
        return result == Result.LOSE;
    }

    private boolean isWin(Result result) {
        return result == Result.WIN;
    }
}
