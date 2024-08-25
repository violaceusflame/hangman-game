package io.github.violaceusflame.mapper;

import io.github.violaceusflame.exception.WordRepositoryValidatorException;

public class WordRepositoryValidatorRusMessageMapper implements MessageMapper<RuntimeException> {
    private static final String EMPTY_WORDS_LIST = "Список слов пуст или не содержит строк со словами";
    private static final String INVALID_WORD_IN_FILE = "Ошибка в файле со списком слов: строка '%s' не является словом, так как содержит символы, отличающиеся от букв кириллицы";

    @Override
    public String apply(RuntimeException e) {
        if (e instanceof WordRepositoryValidatorException wordRepositoryValidatorException) {
            if (wordRepositoryValidatorException.getInvalidWord() == null) {
                return EMPTY_WORDS_LIST;
            }
            return String.format(INVALID_WORD_IN_FILE, wordRepositoryValidatorException.getInvalidWord());
        }
        throw new IllegalArgumentException("Illegal exception: " + e);
    }
}
