package io.github.violaceusflame.validator;

public abstract class AbstractInputValidator implements Validator {
    private static final String ALLOWED_ONLY_ONE_CHARACTER = "Ввести можно только один символ!";

    @Override
    public void validate(String playerInput) {
        if (playerInput.length() != 1) {
            throw new IllegalArgumentException(ALLOWED_ONLY_ONE_CHARACTER);
        }
    }
}
