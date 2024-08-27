package io.github.violaceusflame.validator;

import io.github.violaceusflame.exception.EmptyWordListException;
import io.github.violaceusflame.exception.InvalidWordException;

import java.util.List;

public class WordRepositoryValidator implements Validator<List<String>> {
    private final AbstractLetterValidator letterValidator;

    public WordRepositoryValidator(AbstractLetterValidator letterValidator) {
        this.letterValidator = letterValidator;
    }

    @Override
    public void validate(List<String> words) {
        if (words.isEmpty()) {
            throw new EmptyWordListException();
        }

        for (String word : words) {
            if (!isWord(word)) {
                throw new InvalidWordException(word);
            }
        }
    }

    private boolean isWord(String line) {
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (!letterValidator.isLetterInLanguage(ch)) {
                return false;
            }
        }
        return true;
    }
}
