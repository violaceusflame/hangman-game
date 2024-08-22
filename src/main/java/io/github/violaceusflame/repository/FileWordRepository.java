package io.github.violaceusflame.repository;

import io.github.violaceusflame.exception.OpenWordsFileException;
import io.github.violaceusflame.exception.ReadWordsFileException;
import io.github.violaceusflame.session.HiddenWord;
import io.github.violaceusflame.validator.WordRepositoryValidator;

import java.io.*;
import java.util.List;

public class FileWordRepository implements WordRepository {
    private static final WordRepositoryValidator wordRepositoryValidator = new WordRepositoryValidator();

    private final String fileName;
    private List<String> words;

    public FileWordRepository(String fileName) {
        this.fileName = "/" + fileName;
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
        InputStream resource = FileWordRepository.class.getResourceAsStream(fileName);
        if (resource == null) {
            throw new OpenWordsFileException();
        }
        return resource;
    }

    @Override
    public HiddenWord get() {
        if (words == null) {
            loadWords();
        }

        int randomIndex = getRandomIndex();
        String wordFromList = words.get(randomIndex);
        return new HiddenWord(wordFromList);
    }

    private int getRandomIndex() {
        return (int) (Math.random() * words.size());
    }
}
