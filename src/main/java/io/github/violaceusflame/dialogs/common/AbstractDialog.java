package io.github.violaceusflame.dialogs.common;

import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.mapper.MessageMapper;
import io.github.violaceusflame.validator.Validator;

import java.util.Scanner;

public abstract class AbstractDialog implements Dialog {
    private final Display display;
    private final String title;
    private final Validator<String> validator;
    private final MessageMapper messageMapper;

    public AbstractDialog(Display display, String title, Validator<String> validator, MessageMapper messageMapper) {
        this.display = display;
        this.title = title;
        this.validator = validator;
        this.messageMapper = messageMapper;
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            display.showInfo(title);
            String playerInput = readInput(scanner);
            try {
                validator.validate(playerInput);
                return playerInput;
            } catch (RuntimeException e) {
                String message = messageMapper.apply(e);
                display.showError(message);
            }
        }
    }

    private String readInput(Scanner scanner) {
        String key = scanner.nextLine();
        return key.trim();
    }
}
