package io.github.violaceusflame.launcher;

import io.github.violaceusflame.constant.Language;
import io.github.violaceusflame.dialogs.dialogcenter.DialogCenter;
import io.github.violaceusflame.dialogs.difficultdialog.DifficultDialog;
import io.github.violaceusflame.dialogs.letterdialog.LetterDialog;
import io.github.violaceusflame.dialogs.minmaxdialog.launcherminmaxdialog.LauncherMinMaxDialog;
import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.exception.EmptyWordListException;
import io.github.violaceusflame.exception.InvalidWordException;
import io.github.violaceusflame.exception.OpenWordsFileException;
import io.github.violaceusflame.exception.ReadWordsFileException;
import io.github.violaceusflame.messagecenter.MessageCenter;
import io.github.violaceusflame.repository.WordRepository;
import io.github.violaceusflame.session.HangmanSession;
import io.github.violaceusflame.dialogs.common.dialog.Dialog;
import io.github.violaceusflame.session.HangmanSession.Difficult;
import io.github.violaceusflame.session.HiddenWord;

import java.util.List;
import java.util.Optional;

public class HangmanGameLauncher {
    private enum Key {
        START_TEMPLATE("start_template"),
        DIFFICULT_TEMPLATE("difficult_template"),
        WELCOME("welcome"),
        EXIT("exit"),
        UNABLE_CONTINUE("unable_continue"),
        OPEN_FILE_ERROR("open_file_error"),
        READ_FILE_ERROR("read_file_error"),
        EMPTY_WORD_LIST("empty_word_list"),
        INVALID_COMMAND("invalid_command"),
        INVALID_WORD_TEMPLATE("invalid_word_template");

        public final String section = "Launcher";
        public final String key;

        Key(String key) {
            this.key = key;
        }
    }

    private static final String START_NEW_GAME_COMMAND = "1";
    private static final String EXIT_COMMAND = "2";
    private static final String EASY_DIFFICULT_COMMAND = "1";
    private static final String CLASSIC_DIFFICULT_COMMAND = "2";

    private final WordRepository wordRepository;
    private final Dialog dialog;
    private final Display infoDisplay;
    private final Display errorDisplay;
    private final MessageCenter messageCenter;
    private final DialogCenter dialogCenter;
    private final Language language;
    private boolean running;

    public HangmanGameLauncher(WordRepository wordRepository, Display infoDisplay, Display errorDisplay, Language language, MessageCenter messageCenter, DialogCenter dialogCenter) {
        this.wordRepository = wordRepository;
        this.dialog = new LauncherMinMaxDialog(infoDisplay::show, errorDisplay::show, dialogCenter, ">>> ", 2, 1);
        this.infoDisplay = infoDisplay;
        this.errorDisplay = errorDisplay;
        this.messageCenter = messageCenter;
        this.dialogCenter = dialogCenter;
        this.language = language;
    }

    public HangmanGameLauncher(WordRepository wordRepository, Display display, Language language, MessageCenter messageCenter, DialogCenter dialogCenter) {
        this(wordRepository, display, display, language, messageCenter, dialogCenter);
    }

    public void start() {
        running = true;
        infoDisplay.show(messageCenter.get(Key.WELCOME.section, Key.WELCOME.key));

        while (running) {
            displayStartMessage();
            String playerInput = dialog.getInput();
            chooseAction(playerInput);
        }
    }

    private void displayStartMessage() {
        String startTemplate = messageCenter.get(Key.START_TEMPLATE.section, Key.START_TEMPLATE.key);
        infoDisplay.show(startTemplate.formatted(START_NEW_GAME_COMMAND, EXIT_COMMAND));
    }

    private void chooseAction(String playerInput) {
        switch (playerInput) {
            case START_NEW_GAME_COMMAND:
                startNewGame();
                break;
            case EXIT_COMMAND:
                exit();
                break;
            default:
                errorDisplay.show(messageCenter.get(Key.INVALID_COMMAND.section, Key.INVALID_COMMAND.key));
                break;
        }
    }

    private void startNewGame() {
        Optional<HiddenWord> wordOptional = getHiddenWord();
        if (wordOptional.isEmpty()) {
            return;
        }
        Optional<Difficult> difficultOptional = getDifficult();
        if (difficultOptional.isEmpty()) {
            return;
        }

        HiddenWord word = wordOptional.get();
        Difficult difficult = difficultOptional.get();
        HangmanSession hangmanSession = createHangmanSession(word, difficult);
        hangmanSession.start();
    }

    private Optional<HiddenWord> getHiddenWord() {
        try {
            String text = wordRepository.get();
            HiddenWord hiddenWord = new HiddenWord(text);
            return Optional.of(hiddenWord);
        } catch (RuntimeException e) {
            String exceptionMessage = getExceptionMessage(e).orElseThrow(() -> new IllegalArgumentException("Illegal exception: " + e));
            handleWordRepositoryException(exceptionMessage);
        }
        return Optional.empty();
    }

    private Optional<String> getExceptionMessage(RuntimeException e) {
        if (e instanceof OpenWordsFileException) {
            String message = messageCenter.get(Key.OPEN_FILE_ERROR.section, Key.OPEN_FILE_ERROR.key);
            return Optional.of(message);
        } else if (e instanceof ReadWordsFileException) {
            String message = messageCenter.get(Key.READ_FILE_ERROR.section, Key.READ_FILE_ERROR.key);
            return Optional.of(message);
        } else if (e instanceof EmptyWordListException) {
            String message = messageCenter.get(Key.EMPTY_WORD_LIST.section, Key.EMPTY_WORD_LIST.key);
            return Optional.of(message);
        } else if (e instanceof InvalidWordException invalidWordException) {
            String invalidWordTemplate = messageCenter.get(Key.INVALID_WORD_TEMPLATE.section, Key.INVALID_WORD_TEMPLATE.key);
            String message = invalidWordTemplate.formatted(invalidWordException.getInvalidWord());
            return Optional.of(message);
        }
        return Optional.empty();
    }

    private void handleWordRepositoryException(String exceptionMessage) {
        errorDisplay.show(exceptionMessage);
        errorDisplay.show(messageCenter.get(Key.UNABLE_CONTINUE.section, Key.UNABLE_CONTINUE.key));
        exit();
    }

    private Optional<Difficult> getDifficult() {
        String difficultInput = inputDifficult();
        return switch (difficultInput) {
            case EASY_DIFFICULT_COMMAND -> Optional.of(Difficult.EASY);
            case CLASSIC_DIFFICULT_COMMAND -> Optional.of(Difficult.CLASSIC);
            default -> Optional.empty();
        };
    }

    private String inputDifficult() {
        String difficultDialogMessage = getDifficultMessage();
        String[] difficultCommands = List.of(EASY_DIFFICULT_COMMAND, CLASSIC_DIFFICULT_COMMAND).toArray(String[]::new);
        Dialog difficultDialog = new DifficultDialog(infoDisplay::show, errorDisplay::show, dialogCenter, difficultDialogMessage, difficultCommands);
        return difficultDialog.getInput();
    }

    private String getDifficultMessage() {
        String difficult_template = messageCenter.get(Key.DIFFICULT_TEMPLATE.section, Key.DIFFICULT_TEMPLATE.key);
        return String.format("""
                %s
                >>>""", difficult_template.formatted(
                EASY_DIFFICULT_COMMAND, Difficult.EASY.MAX_ATTEMPTS,
                CLASSIC_DIFFICULT_COMMAND, Difficult.CLASSIC.MAX_ATTEMPTS));
    }

    private HangmanSession createHangmanSession(HiddenWord word, Difficult difficult) {
        Dialog sessionDialog = new LetterDialog(infoDisplay::show, errorDisplay::show, dialogCenter, language, ">>> ");
        return new HangmanSession(difficult, word, sessionDialog, infoDisplay, errorDisplay, messageCenter);
    }

    private void exit() {
        infoDisplay.show(messageCenter.get(Key.EXIT.section, Key.EXIT.key));
        running = false;
    }
}
