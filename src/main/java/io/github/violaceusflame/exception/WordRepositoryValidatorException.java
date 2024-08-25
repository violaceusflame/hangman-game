package io.github.violaceusflame.exception;

public class WordRepositoryValidatorException extends RuntimeException {
    private String invalidWord;

    public WordRepositoryValidatorException(String message) {
        super(message);
    }

    public WordRepositoryValidatorException(String message, String invalidWord) {
        super(message);
        this.invalidWord = invalidWord;
    }

    public String getInvalidWord() {
        return invalidWord;
    }
}
