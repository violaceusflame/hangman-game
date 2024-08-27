package io.github.violaceusflame.exception;

public class EmptyWordListException extends RuntimeException {
    private static final String EMPTY_WORD_LIST_MESSAGE = "Word list is empty or not contains strings with words";

    public EmptyWordListException() {
        super(EMPTY_WORD_LIST_MESSAGE);
    }
}
