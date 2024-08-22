package io.github.violaceusflame.exception;

public class OpenWordsFileException extends RuntimeException {
    private static final String OPEN_FILE_ERROR = "Ошибка при открытии файла со списком слов";

    public OpenWordsFileException() {
        super(OPEN_FILE_ERROR);
    }
}
