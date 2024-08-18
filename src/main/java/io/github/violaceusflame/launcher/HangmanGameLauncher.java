package io.github.violaceusflame.launcher;

import io.github.violaceusflame.session.HangmanSession;
import io.github.violaceusflame.repository.WordRepository;
import io.github.violaceusflame.dialog.Dialog;
import io.github.violaceusflame.dialog.LauncherDialog;
import io.github.violaceusflame.session.HangmanSession.Difficult;
import io.github.violaceusflame.session.HiddenWord;

import java.util.Optional;

import static io.github.violaceusflame.repository.WordRepository.FILE_READ_ERROR;

public class HangmanGameLauncher {
    private static final String WELCOME_MESSAGE = "Добро пожаловать в Виселицу!";
    private static final String START_NEW_GAME_COMMAND = "1";
    private static final String EXIT_COMMAND = "2";
    private static final String EASY_DIFFICULT_COMMAND = "1";
    private static final String CLASSIC_DIFFICULT_COMMAND = "2";
    private static final String EXIT_MESSAGE = "Выходим из игры...";
    private static final String INVALID_COMMAND_MESSAGE = "Такого пункта меню нет! Повторите попытку";
    private static final Dialog dialog = new LauncherDialog();

    private boolean running;

    public void run() {
        running = true;
        dialog.display(WELCOME_MESSAGE);

        while (running) {
            displayStartMessage();

            String playerInput;
            try {
                playerInput = dialog.getInput();
                chooseAction(playerInput);
            } catch (IllegalArgumentException e) {
                dialog.display(e.getMessage());
            }
        }
    }

    private void displayStartMessage() {
        dialog.display(String.format("""
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
                dialog.display(INVALID_COMMAND_MESSAGE);
                break;
        }
    }

    private void startNewGame() {
        HiddenWord word;
        try {
            word = WordRepository.get();
        } catch (RuntimeException e) {
            handleWordRepositoryException(e);
            return;
        }

        Difficult difficult = getDifficult();
        HangmanSession hangmanSession = createHangmanSession(word, difficult);
        hangmanSession.start();
    }

    private void handleWordRepositoryException(RuntimeException e) {
        Optional<String> exceptionMessage = getExceptionMessage(e);
        if (exceptionMessage.isPresent()) {
            dialog.display(exceptionMessage.get());
        } else {
            dialog.display(FILE_READ_ERROR);
        }
        dialog.display("Продолжать игру невозможно.");
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

            String difficult;
            try {
                difficult = dialog.getInput();
            } catch (IllegalArgumentException e) {
                dialog.display(e.getMessage());
                continue;
            }

            switch (difficult) {
                case EASY_DIFFICULT_COMMAND:
                    return Difficult.EASY;
                case CLASSIC_DIFFICULT_COMMAND:
                    return Difficult.CLASSIC;
                default:
                    dialog.display(INVALID_COMMAND_MESSAGE);
            }
        }
    }

    private HangmanSession createHangmanSession(HiddenWord word, Difficult difficult) {
        return new HangmanSession(difficult, word);
    }

    private void displayDifficultLevels() {
        dialog.display(String.format("""
                        Выберите уровень сложности:
                        [%s] Лёгкий (попыток: %d)
                        [%s] Классический (попыток: %d)""",
                EASY_DIFFICULT_COMMAND, Difficult.EASY.MAX_ATTEMPTS,
                CLASSIC_DIFFICULT_COMMAND, Difficult.CLASSIC.MAX_ATTEMPTS));
    }

    private void exit() {
        dialog.display(EXIT_MESSAGE);
        running = false;
    }
}
