package io.github.violaceusflame.validator;

public class HangmanSessionInputValidator extends AbstractInputValidator {
    private static final String ALLOWED_ONLY_LETTERS = "Вводить можно только буквы!";
    private static final String LETTER_NOT_CYRILLIC = "Допустимы только буквы кириллицы!";

    @Override
    public void validate(String playerInput) {
        super.validate(playerInput);
        if (!isLetter(playerInput)) {
            throw new IllegalArgumentException(ALLOWED_ONLY_LETTERS);
        }
        if (!isLetterCyrillic(playerInput)) {
            throw new IllegalArgumentException(LETTER_NOT_CYRILLIC);
        }
    }

    private boolean isLetterCyrillic(String playerInput) {
        return Character.UnicodeBlock.of(playerInput.charAt(0)).equals(Character.UnicodeBlock.CYRILLIC);
    }

    private boolean isLetter(String playerInput) {
        return Character.isLetter(playerInput.charAt(0));
    }
}
