package io.github.violaceusflame.validator;

import io.github.violaceusflame.exception.MoreCharactersInputException;
import io.github.violaceusflame.exception.NotDigitException;

public class LauncherInputValidator implements Validator<String> {
    @Override
    public void validate(String playerInput) {
        if (playerInput.length() != 1) {
            throw new MoreCharactersInputException();
        }
        if (!isDigit(playerInput)) {
            throw new NotDigitException();
        }
    }

    private boolean isDigit(String playerInput) {
        return Character.isDigit(playerInput.charAt(0));
    }
}
