package io.github.violaceusflame.dialog;

import io.github.violaceusflame.validator.Validator;

import java.util.Scanner;

public abstract class AbstractDialog implements Dialog {
    protected final Validator<String> inputValidator;

    public AbstractDialog(Validator<String> inputValidator) {
        this.inputValidator = inputValidator;
    }

    @Override
    public String getInput() {
        String playerInput = readInput();
        inputValidator.validate(playerInput);
        return playerInput;
    }

    private String readInput() {
        System.out.print(">>> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}
