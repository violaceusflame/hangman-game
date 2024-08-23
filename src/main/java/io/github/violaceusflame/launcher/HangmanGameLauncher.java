package io.github.violaceusflame.launcher;

import io.github.violaceusflame.dialog.HangmanSessionDialog;
import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.repository.WordRepository;
import io.github.violaceusflame.session.HangmanSession;
import io.github.violaceusflame.dialog.Dialog;
import io.github.violaceusflame.session.HangmanSession.Difficult;
import io.github.violaceusflame.session.HiddenWord;

import java.util.Optional;

public class HangmanGameLauncher {
    private static final String WELCOME_MESSAGE = "Добро пожаловать в Виселицу!";
    private static final String START_NEW_GAME_COMMAND = "1";
    private static final String EXIT_COMMAND = "2";
    private static final String EASY_DIFFICULT_COMMAND = "1";
    private static final String CLASSIC_DIFFICULT_COMMAND = "2";
    private static final String EXIT_MESSAGE = "Выходим из игры...";
    private static final String INVALID_COMMAND_MESSAGE = "Неизвестная команда";
    private static final String HIDDEN_WORD_GET_ERROR = "Ошибка при получении случайного тайного слова";

    private final WordRepository wordRepository;
    private final Dialog dialog;
    private final Display display;
    private boolean running;

    public HangmanGameLauncher(WordRepository wordRepository, Dialog dialog, Display display) {
        this.wordRepository = wordRepository;
        this.dialog = dialog;
        this.display = display;
    }

    public void start() {
        running = true;
        display.show(WELCOME_MESSAGE);

        while (running) {
            displayStartMessage();
            String playerInput = getPlayerInput();
            chooseAction(playerInput);
        }
    }

    private String getPlayerInput() {
        while (true) {
            try {
                return dialog.getInput();
            } catch (IllegalArgumentException e) {
                display.show(e.getMessage());
            }
        }
    }

    private void displayStartMessage() {
        display.show(String.format("""
                        Выберите пункт меню:
                        [%s] Новая игра
                        [%s] Выход""",
                START_NEW_GAME_COMMAND, EXIT_COMMAND));
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
                display.show(INVALID_COMMAND_MESSAGE);
                break;
        }
    }

    private void startNewGame() {
        Optional<HiddenWord> wordOptional = getHiddenWord();
        if (wordOptional.isEmpty()) {
            return;
        }

        HiddenWord word = wordOptional.get();
        Difficult difficult = getDifficult();
        HangmanSession hangmanSession = createHangmanSession(word, difficult);
        hangmanSession.start();
    }

    private Optional<HiddenWord> getHiddenWord() {
        try {
            return Optional.of(wordRepository.get());
        } catch (RuntimeException e) {
            String exceptionMessage = getExceptionMessage(e).orElse(HIDDEN_WORD_GET_ERROR);
            handleWordRepositoryException(exceptionMessage);
            return Optional.empty();
        }
    }

    private void handleWordRepositoryException(String exceptionMessage) {
        display.show(exceptionMessage);
        display.show("Продолжать игру невозможно.");
        exit();
    }

    private Optional<String> getExceptionMessage(RuntimeException e) {
        if (e.getMessage() == null) {
            return Optional.empty();
        }

        String exceptionMessage = e.getMessage();
        if (e.getCause() != null) {
            String causeExceptionMessage = e.getCause().getMessage();
            exceptionMessage += ": " + causeExceptionMessage;
        }
        return Optional.of(exceptionMessage);
    }

    private Difficult getDifficult() {
        while (true) {
            displayDifficultLevels();

            String difficult = getPlayerInput();
            switch (difficult) {
                case EASY_DIFFICULT_COMMAND:
                    return Difficult.EASY;
                case CLASSIC_DIFFICULT_COMMAND:
                    return Difficult.CLASSIC;
                default:
                    display.show(INVALID_COMMAND_MESSAGE);
            }
        }
    }

    private HangmanSession createHangmanSession(HiddenWord word, Difficult difficult) {
        Dialog sessionDialog = new HangmanSessionDialog();
        return new HangmanSession(difficult, word, sessionDialog, display);
    }

    private void displayDifficultLevels() {
        display.show(String.format("""
                        Выберите уровень сложности:
                        [%s] Лёгкий (попыток: %d)
                        [%s] Классический (попыток: %d)""",
                EASY_DIFFICULT_COMMAND, Difficult.EASY.MAX_ATTEMPTS,
                CLASSIC_DIFFICULT_COMMAND, Difficult.CLASSIC.MAX_ATTEMPTS));
    }

    private void exit() {
        display.show(EXIT_MESSAGE);
        running = false;
    }
}
