package io.github.violaceusflame.dialogs.minmaxintdialog;

import io.github.violaceusflame.dialogs.common.MoreCharactersInputException;
import io.github.violaceusflame.dialogs.minmaxintdialog.exception.NotDigitException;
import io.github.violaceusflame.dialogs.minmaxintdialog.exception.NumberGreaterThanMaxException;
import io.github.violaceusflame.dialogs.minmaxintdialog.exception.NumberLessThanMinException;
import io.github.violaceusflame.validator.Validator;

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
