package io.github.violaceusflame.validator;

import io.github.violaceusflame.constant.Language;
import io.github.violaceusflame.dialogs.common.AbstractLetterValidator;
import io.github.violaceusflame.dialogs.common.Validator;
import io.github.violaceusflame.dialogs.letterdialog.en.EnLetterValidator;
import io.github.violaceusflame.dialogs.letterdialog.pl.PlLetterValidator;
import io.github.violaceusflame.dialogs.letterdialog.ru.RuLetterValidator;
import io.github.violaceusflame.dialogs.letterdialog.ua.UaLetterValidator;
import io.github.violaceusflame.exception.EmptyWordListException;
import io.github.violaceusflame.exception.InvalidWordException;

import java.util.List;

public class WordRepositoryValidator implements Validator<List<String>> {
    private final AbstractLetterValidator letterValidator;

    public WordRepositoryValidator(Language language) {
        this.letterValidator = createValidator(language);
    }

    private AbstractLetterValidator createValidator(Language language) {
        return switch (language) {
            case RU -> new RuLetterValidator();
            case EN -> new EnLetterValidator();
            case PL -> new PlLetterValidator();
            case UA -> new UaLetterValidator();
        };
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
