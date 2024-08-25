package io.github.violaceusflame.launcher;

import io.github.violaceusflame.dialog.EngLetterDialog;
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
    private final MessageMapper<RuntimeException> messageMapper;
    private boolean running;

    // TODO: Сделать обработку исключений у объектов, внедряемых через DI.
    // TODO: WordRepository внедряется через DI (Dependency Injection).
    //  Надо подумать над тем, как обрабатывать исключения такого объекта. Как вариант, можно включить метод
    //  для обработки исключений в интерфейс и в случае возникновения вызывать его.
    //  Нечто похожее есть в Spring: там можно аннотировать методы @ExceptionHandler(...).
    //  Или можно написать отдельный объект-обработчик в дополнение к конкретной реализации WordRepository
    //  и там уже производить маппинг в конкретное текстовое представление, которое будет отображаться на экране.
    // TODO: А вообще нужно почитать про DI внимательнее.
    // TODO: Можно написать маппер, аналогичный тому, который Алексей использовал при написании своего варианта диалога.
    public HangmanGameLauncher(WordRepository wordRepository, Dialog dialog, Display display, MessageMapper<RuntimeException> messageMapper) {
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
//            String exceptionMessage = getExceptionMessage(e).orElse(HIDDEN_WORD_GET_ERROR);
            // TODO: Проверить корректность перехвата исключений WordRepositoryValidator
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
