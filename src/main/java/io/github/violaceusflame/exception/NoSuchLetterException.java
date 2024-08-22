package io.github.violaceusflame.exception;

public class NoSuchLetterException extends RuntimeException {
    private static final String NO_SUCH_LETTER_ERROR = "Ошибка: буквы %s нет в загаданном слове!";

    public NoSuchLetterException(String wrongLetter) {
        super(String.format(NO_SUCH_LETTER_ERROR, wrongLetter));
    }
}
