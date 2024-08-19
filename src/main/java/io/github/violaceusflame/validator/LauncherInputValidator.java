package io.github.violaceusflame.validator;

public class LauncherInputValidator extends AbstractInputValidator {
    public static final String ALLOWED_ONLY_DIGIT = "Ввести можно только цифру, соответствующую пункту меню!";

    @Override
    public void validate(String playerInput) {
        super.validate(playerInput);
        if (!isDigit(playerInput)) {
            throw new IllegalArgumentException(ALLOWED_ONLY_DIGIT);
        }
    }

    private boolean isDigit(String playerInput) {
        return Character.isDigit(playerInput.charAt(0));
    }
}
