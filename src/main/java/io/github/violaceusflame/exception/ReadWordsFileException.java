package io.github.violaceusflame.exception;

public class ReadWordsFileException extends RuntimeException {
    private static final String READ_FILE_ERROR = "Ошибка при чтении файла со списком слов";

    public ReadWordsFileException(Throwable cause) {
        super(READ_FILE_ERROR, cause);
    }
}
