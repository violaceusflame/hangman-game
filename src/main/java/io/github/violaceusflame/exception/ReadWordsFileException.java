package io.github.violaceusflame.exception;

public class ReadWordsFileException extends RuntimeException {
    private static final String READ_FILE_ERROR = "An error occurred when reading a file with a list of words";

    public ReadWordsFileException(Throwable cause) {
        super(READ_FILE_ERROR, cause);
    }
}
