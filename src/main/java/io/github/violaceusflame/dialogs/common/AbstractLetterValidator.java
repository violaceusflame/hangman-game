package io.github.violaceusflame.dialogs.common;

import io.github.violaceusflame.dialogs.letterdialog.exception.NotLetterException;
import io.github.violaceusflame.dialogs.letterdialog.exception.NotLetterInLanguageException;

import java.lang.Character.UnicodeBlock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractLetterValidator implements Validator<String> {
    private final List<UnicodeBlock> unicodeBlocks = new ArrayList<>();

    public AbstractLetterValidator(UnicodeBlock unicodeBlock) {
        unicodeBlocks.add(unicodeBlock);
    }

    public AbstractLetterValidator(UnicodeBlock... unicodeBlocks) {
        this.unicodeBlocks.addAll(Arrays.asList(unicodeBlocks));
    }

    @Override
    public void validate(String playerInput) {
        if (playerInput.length() != 1) {
            throw new MoreCharactersInputException();
        }
        char typedLetter = playerInput.charAt(0);
        if (!isLetter(typedLetter)) {
            throw new NotLetterException();
        }
        if (!isLetterInLanguage(typedLetter)) {
            throw new NotLetterInLanguageException();
        }
    }

    public boolean isLetterInLanguage(char letter) {
        Predicate<UnicodeBlock> predicate = unicodeBlock -> isLetter(letter) && UnicodeBlock.of(letter).equals(unicodeBlock);
        return unicodeBlocks.stream().anyMatch(predicate);
    }

    public boolean isLetter(char letter) {
        return Character.isLetter(letter);
    }
}
