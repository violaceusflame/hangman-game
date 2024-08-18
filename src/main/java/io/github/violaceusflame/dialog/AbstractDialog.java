package io.github.violaceusflame.dialog;

import io.github.violaceusflame.validator.dialog.Validator;

import java.util.Scanner;

public class AbstractDialog implements Dialog {
    protected final Validator inputValidator;

    public AbstractDialog(Validator inputValidator) {
        this.inputValidator = inputValidator;
    }

    @Override
    public String getInput() {
        System.out.print(">>> ");
        Scanner scanner = new Scanner(System.in);
        String playerInput = scanner.nextLine().trim();
        inputValidator.validate(playerInput);
        return playerInput;
    }

    @Override
    public void display(String info) {
        System.out.println(info);
    }
}
