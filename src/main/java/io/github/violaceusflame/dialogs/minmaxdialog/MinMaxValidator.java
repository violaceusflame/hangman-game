package io.github.violaceusflame.dialogs.minmaxdialog;

import io.github.violaceusflame.dialogs.common.MoreCharactersInputException;
import io.github.violaceusflame.dialogs.minmaxdialog.exception.NotDigitException;
import io.github.violaceusflame.dialogs.minmaxdialog.exception.NumberGreaterThanMaxException;
import io.github.violaceusflame.dialogs.minmaxdialog.exception.NumberLessThanMinException;
import io.github.violaceusflame.dialogs.common.Validator;

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

        int typedNumber = parseInt(playerInput);
        if (typedNumber < min) {
            throw new NumberLessThanMinException(typedNumber);
        }
        if (typedNumber > max) {
            throw new NumberGreaterThanMaxException(typedNumber);
        }
    }

    private int parseInt(String playerInput) {
        try {
            return Integer.parseInt(playerInput);
        } catch (NumberFormatException e) {
            throw new NotDigitException(e);
        }
    }

    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }
}
