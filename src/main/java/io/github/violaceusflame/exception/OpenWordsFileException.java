package io.github.violaceusflame.exception;

public class OpenWordsFileException extends RuntimeException {
    private static final String OPEN_FILE_ERROR = "An error occurred when opening a file with a list of words";

    public OpenWordsFileException() {
        super(OPEN_FILE_ERROR);
    }
}
