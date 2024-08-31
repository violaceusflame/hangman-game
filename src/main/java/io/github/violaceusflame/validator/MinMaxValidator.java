package io.github.violaceusflame.validator;

import io.github.violaceusflame.exception.MoreCharactersInputException;
import io.github.violaceusflame.exception.NotDigitException;
import io.github.violaceusflame.exception.NumberGreaterThanMaxException;
import io.github.violaceusflame.exception.NumberLessThanMinException;

public class MinMaxValidator implements Validator<String> {
    private final int min;
    private final int max;

    public MinMaxValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(String playerInput) {
        if (playerInput.length() != 1) {
            throw new MoreCharactersInputException();
        }

        char typedChar = playerInput.charAt(0);
        if (!isDigit(typedChar)) {
            throw new NotDigitException();
        }

        int typedNumber = Integer.parseInt(playerInput);
        if (typedNumber < min) {
            throw new NumberLessThanMinException(typedNumber);
        }
        if (typedNumber > max) {
            throw new NumberGreaterThanMaxException(typedNumber);
        }
    }

    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }
}
