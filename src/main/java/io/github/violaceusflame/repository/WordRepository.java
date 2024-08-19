package io.github.violaceusflame.repository;

import io.github.violaceusflame.session.HiddenWord;
import io.github.violaceusflame.validator.WordRepositoryValidator;

import java.io.*;
import java.util.List;

public class WordRepository {
    public static final String FILE_READ_ERROR = "Ошибка при чтении файла со списком слов";
    private static final String PATH_TO_WORDS_FILE = "src/main/resources/words.txt";
    private static List<String> words;

    private static void loadWords() {
        try (var reader = new BufferedReader(new FileReader(PATH_TO_WORDS_FILE))) {
            words = reader.lines()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();
            WordRepositoryValidator.validate(words);
        } catch (IOException e) {
            throw new RuntimeException(FILE_READ_ERROR, e);
        }
    }

    public static HiddenWord get() {
        if (words == null) {
            loadWords();
        }

        int randomIndex = getRandomIndex();
        String wordFromList = words.get(randomIndex);
        return new HiddenWord(wordFromList);
    }

    private static int getRandomIndex() {
        return (int) (Math.random() * words.size());
    }

    private WordRepository() {
    }
}
