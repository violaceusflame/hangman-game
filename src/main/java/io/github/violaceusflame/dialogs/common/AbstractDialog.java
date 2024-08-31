package io.github.violaceusflame.dialogs.common;

import io.github.violaceusflame.dialogs.common.printer.Printer;
import io.github.violaceusflame.mapper.MessageMapper;
import io.github.violaceusflame.validator.Validator;

import java.util.Scanner;

public abstract class AbstractDialog implements Dialog {
    private final Printer printer;
    private final String title;
    private final Validator<String> validator;
    private final MessageMapper messageMapper;

    public AbstractDialog(Printer printer, String title, Validator<String> validator, MessageMapper messageMapper) {
        this.printer = printer;
        this.title = title;
        this.validator = validator;
        this.messageMapper = messageMapper;
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printer.printInfo(title);
            String playerInput = readInput(scanner);
            try {
                validator.validate(playerInput);
                return playerInput;
            } catch (RuntimeException e) {
                String message = messageMapper.apply(e);
                printer.printError(message);
            }
        }
    }

    private String readInput(Scanner scanner) {
        String key = scanner.nextLine();
        return key.trim();
    }
}
