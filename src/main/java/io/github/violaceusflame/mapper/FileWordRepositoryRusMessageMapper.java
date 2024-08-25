package io.github.violaceusflame.mapper;

import io.github.violaceusflame.exception.OpenWordsFileException;
import io.github.violaceusflame.exception.ReadWordsFileException;

public class FileWordRepositoryRusMessageMapper implements MessageMapper<RuntimeException> {
    private static final String OPEN_FILE_ERROR_MESSAGE = "Ошибка при открытии файла со списком слов";
    private static final String READ_FILE_ERROR_MESSAGE = "Ошибка при чтении файла со списком слов";
    private static final WordRepositoryValidatorRusMessageMapper validatorMessageMapper = new WordRepositoryValidatorRusMessageMapper();

    @Override
    public String apply(RuntimeException e) {
        if (e instanceof OpenWordsFileException) {
            return OPEN_FILE_ERROR_MESSAGE;
        } else if (e instanceof ReadWordsFileException) {
            return READ_FILE_ERROR_MESSAGE;
        }
        return validatorMessageMapper.apply(e);
    }
}
