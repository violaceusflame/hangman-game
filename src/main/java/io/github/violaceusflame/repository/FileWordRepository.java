package io.github.violaceusflame.repository;

import io.github.violaceusflame.constant.Language;
import io.github.violaceusflame.exception.OpenWordsFileException;
import io.github.violaceusflame.exception.ReadWordsFileException;
import io.github.violaceusflame.messagecenter.MessageCenter;
import io.github.violaceusflame.validator.WordRepositoryValidator;

import java.io.*;
import java.util.List;

public class FileWordRepository implements WordRepository {
    private final WordRepositoryValidator wordRepositoryValidator;
    private final String filename;
    private List<String> words;

    public FileWordRepository(String directory, String filenameTemplate, Language language) {
        this.filename = String.format("/%s/%s", directory, filenameTemplate.formatted(language.name().toLowerCase()));
        this.wordRepositoryValidator = new WordRepositoryValidator(language);
    }

    @Override
    public String get() {
        if (words == null) {
            loadWords();
        }

        int randomIndex = getRandomIndex();
        return words.get(randomIndex);
    }

    private void loadWords() {
        InputStream wordsFileAsStream = getWordsFileAsStream();
        try (var reader = new BufferedReader(new InputStreamReader(wordsFileAsStream))) {
            words = reader.lines()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();
            wordRepositoryValidator.validate(words);
        } catch (IOException e) {
            throw new ReadWordsFileException(e);
        }
    }

    private InputStream getWordsFileAsStream() {
        InputStream resource = FileWordRepository.class.getResourceAsStream(filename);
        if (resource == null) {
            throw new OpenWordsFileException();
        }
        return resource;
    }

    private int getRandomIndex() {
        return (int) (Math.random() * words.size());
    }
}
