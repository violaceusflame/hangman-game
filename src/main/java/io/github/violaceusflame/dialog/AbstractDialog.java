package io.github.violaceusflame.dialog;

import io.github.violaceusflame.display.Display;
import io.github.violaceusflame.exception.ValidatorException;
import io.github.violaceusflame.mapper.MessageMapper;
import io.github.violaceusflame.validator.Validator;

import java.util.Scanner;

public abstract class AbstractDialog implements Dialog {
    private final Display display;
    private final String title;
    private final Validator<String> validator;
    private final MessageMapper<ValidatorException> messageMapper;

    public AbstractDialog(Display display, String title, Validator<String> validator, MessageMapper<ValidatorException> messageMapper) {
        this.display = display;
        this.title = title;
        this.validator = validator;
        this.messageMapper = messageMapper;
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            display.show(title);
            String playerInput = readInput(scanner);
            try {
                validator.validate(playerInput);
                return playerInput;
            } catch (ValidatorException e) {
                String message = messageMapper.apply(e);
                display.show(message);
            }
        }
    }

    private String readInput(Scanner scanner) {
        String key = scanner.nextLine();
        return key.trim();
    }
}
