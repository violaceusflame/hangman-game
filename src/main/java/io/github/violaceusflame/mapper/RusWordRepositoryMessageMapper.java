package io.github.violaceusflame.mapper;

import io.github.violaceusflame.exception.EmptyWordListException;
import io.github.violaceusflame.exception.InvalidWordException;

public class RusWordRepositoryMessageMapper implements MessageMapper {
    private static final String EMPTY_WORD_LIST_MESSAGE = "Список слов пуст или не содержит строк со словами";
    private static final String INVALID_WORD_MESSAGE = "Ошибка: строка '%s' не является словом, так как содержит некорректные символы";

    @Override
    public String apply(RuntimeException e) {
        if (e instanceof EmptyWordListException) {
            return EMPTY_WORD_LIST_MESSAGE;
        } else if (e instanceof InvalidWordException invalidWordException) {
            return String.format(INVALID_WORD_MESSAGE, invalidWordException.getInvalidWord());
        }

        throw new IllegalArgumentException("Invalid exception: " + e);
    }
}
