package io.github.violaceusflame.launcher;

import io.github.violaceusflame.dialog.RusLetterDialog;
import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.mapper.MessageMapper;
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

    private final WordRepository wordRepository;
    private final Dialog dialog;
    private final Display display;
    private final MessageMapper messageMapper;
    private boolean running;

    public HangmanGameLauncher(WordRepository wordRepository, Dialog dialog, Display display, MessageMapper messageMapper) {
        this.wordRepository = wordRepository;
        this.dialog = dialog;
        this.display = display;
        this.messageMapper = messageMapper;
    }

    public void start() {
        running = true;
        display.show(WELCOME_MESSAGE);

        while (running) {
            displayStartMessage();
            String playerInput = dialog.getInput();
            chooseAction(playerInput);
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
            String exceptionMessage = messageMapper.apply(e);
            handleWordRepositoryException(exceptionMessage);
            return Optional.empty();
        }
    }

    private void handleWordRepositoryException(String exceptionMessage) {
        display.show(exceptionMessage);
        display.show("Продолжать игру невозможно.");
        exit();
    }

    private Difficult getDifficult() {
        while (true) {
            displayDifficultLevels();

            String difficult = dialog.getInput();
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
        Dialog sessionDialog = new RusLetterDialog(display, "Ввод: ");
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
