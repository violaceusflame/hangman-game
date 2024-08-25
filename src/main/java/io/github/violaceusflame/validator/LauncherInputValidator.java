package io.github.violaceusflame.validator;

import io.github.violaceusflame.exception.ValidatorException;

public class LauncherInputValidator implements Validator<String> {
    @Override
    public void validate(String playerInput) {
        if (playerInput.length() != 1) {
            throw new ValidatorException(Result.ALLOWED_ONLY_ONE_CHARACTER);
        }
        if (!isDigit(playerInput)) {
            throw new ValidatorException(Result.ALLOWED_ONLY_DIGIT);
        }
    }

    private boolean isDigit(String playerInput) {
        return Character.isDigit(playerInput.charAt(0));
    }

    public enum Result {
        ALLOWED_ONLY_ONE_CHARACTER,
        ALLOWED_ONLY_DIGIT
    }
}
