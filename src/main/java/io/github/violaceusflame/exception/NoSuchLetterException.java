package io.github.violaceusflame.exception;

public class NoSuchLetterException extends RuntimeException {
    private static final String NO_SUCH_LETTER_ERROR = "The letter %s is not in the hidden word";
    private final String wrongLetter;

    public NoSuchLetterException(String wrongLetter) {
        super(String.format(NO_SUCH_LETTER_ERROR, wrongLetter));
        this.wrongLetter = wrongLetter;
    }

    public String getWrongLetter() {
        return wrongLetter;
    }
}
