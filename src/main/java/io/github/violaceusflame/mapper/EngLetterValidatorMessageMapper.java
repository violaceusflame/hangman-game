package io.github.violaceusflame.mapper;

import io.github.violaceusflame.exception.ValidatorException;
import io.github.violaceusflame.validator.RusLetterValidator;

public class EngLetterValidatorMessageMapper implements MessageMapper<ValidatorException> {
    @Override
    public String apply(ValidatorException e) {
        RusLetterValidator.Result value = (RusLetterValidator.Result) e.getValue();
        return Converter.getByResult(value);
    }

    private enum Converter {
        ALLOWED_ONLY_LETTERS(RusLetterValidator.Result.ALLOWED_ONLY_LETTERS, "Вводить можно только буквы!"),
        LETTER_NOT_CYRILLIC(RusLetterValidator.Result.LETTER_NOT_IN_LANGUAGE, "Допустимы только буквы латиницы!"),
        MORE_LETTERS(RusLetterValidator.Result.MORE_LETTERS, "Можно ввести только одну букву");

        private final RusLetterValidator.Result result;
        private final String message;

        Converter(RusLetterValidator.Result result, String message) {
            this.result = result;
            this.message = message;
        }

        public static String getByResult(RusLetterValidator.Result result) {
            Converter[] converters = Converter.values();

            for (Converter converter : converters) {
                if (result == converter.result) {
                    return converter.message;
                }
            }

            throw new IllegalArgumentException("Illegal: " + result);
        }
    }
}
