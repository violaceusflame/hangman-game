package io.github.violaceusflame.dialogs.common.dialog;

import io.github.violaceusflame.dialogs.common.Printer;
import io.github.violaceusflame.dialogs.common.messagemapper.MessageMapper;
import io.github.violaceusflame.dialogs.common.Validator;

import java.util.Scanner;

public abstract class AbstractDialog implements Dialog {
    private final Printer infoPrinter;
    private final Printer errorPrinter;
    private final String title;
    private final Validator<String> validator;
    private final MessageMapper messageMapper;

    public AbstractDialog(Printer infoPrinter, Printer errorPrinter, String title, MessageMapper messageMapper, Validator<String> validator) {
        this.infoPrinter = infoPrinter;
        this.errorPrinter = errorPrinter;
        this.title = title;
        this.validator = validator;
        this.messageMapper = messageMapper;
    }

    public AbstractDialog(Printer printer, String title, MessageMapper messageMapper, Validator<String> validator) {
        this(printer, printer, title, messageMapper, validator);
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            infoPrinter.print(title);
            String playerInput = readInput(scanner);
            try {
                validator.validate(playerInput);
                return playerInput;
            } catch (RuntimeException e) {
                String message = messageMapper.apply(e);
                errorPrinter.print(message);
            }
        }
    }

    private String readInput(Scanner scanner) {
        String key = scanner.nextLine();
        return key.trim();
    }
}
