package io.github.violaceusflame.validator;

import java.util.List;

public class WordRepositoryValidator {
    private static final String EMPTY_WORDS_LIST = "Список слов пуст или не содержит строк со словами";
    private static final String INVALID_WORD_IN_FILE = "Ошибка в файле со списком слов: строка '%s' не является словом, так как содержит символы, отличающиеся от букв кириллицы";

    public static void validate(List<String> words) {
        if (words.isEmpty()) {
            throw new RuntimeException(EMPTY_WORDS_LIST);
        }

        for (String word : words) {
            if (!isWord(word)) {
                throw new RuntimeException(String.format(INVALID_WORD_IN_FILE, word));
            }
        }
    }

    private static boolean isWord(String line) {
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (!isCyrillicLetter(ch)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCyrillicLetter(char ch) {
        return Character.isLetter(ch) && isCyrillic(ch);
    }

    private static boolean isCyrillic(char letter) {
        return Character.UnicodeBlock.of(letter).equals(Character.UnicodeBlock.CYRILLIC);
    }
}
