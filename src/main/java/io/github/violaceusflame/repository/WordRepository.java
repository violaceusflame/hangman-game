package io.github.violaceusflame.repository;

import io.github.violaceusflame.session.HiddenWord;
import io.github.violaceusflame.validator.WordRepositoryValidator;

import java.io.*;
import java.net.URL;
import java.util.List;

// TODO: Путь к файлу должен передаваться в конструктор, а не быть жестко прошитым внутри класса
public class WordRepository {
    // TODO: Полагаю, здесь тоже имеет некоторая зависимость от представления.
    public static final String FILE_READ_ERROR = "Ошибка при чтении файла со списком слов";
    private static final String PATH_TO_WORDS_FILE = "src/main/resources/words.txt";
    private static final WordRepositoryValidator wordRepositoryValidator = new WordRepositoryValidator();
    private static List<String> words;

    private static void loadWords() {
        try (var reader = new BufferedReader(new FileReader(getFileResource()))) {
            words = reader.lines()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();
            wordRepositoryValidator.validate(words);
        } catch (IOException e) {
            throw new RuntimeException(FILE_READ_ERROR, e);
        }
    }

    private static File getFileResource() {
        URL resource = WordRepository.class.getResource("/words.txt");
        if (resource == null) {
            throw new RuntimeException(FILE_READ_ERROR);
        }
        return new File(resource.getFile());
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
