package io.github.violaceusflame.dialogs.optiondialog;

import io.github.violaceusflame.dialogs.optiondialog.exception.InputDoesNotMatchWithOptionsException;
import io.github.violaceusflame.dialogs.common.Validator;

import java.util.List;

public class OptionValidator implements Validator<String> {
    private final List<String> options;

    public OptionValidator(List<String> options) {
        this.options = options;
    }

    @Override
    public void validate(String input) {
        if (!containsIgnoreCase(options, input)) {
            throw new InputDoesNotMatchWithOptionsException();
        }
    }

    private boolean containsIgnoreCase(List<String> options, String input) {
        for (String option : options) {
            if (input.equalsIgnoreCase(option)) {
                return true;
            }
        }
        return false;
    }
}
