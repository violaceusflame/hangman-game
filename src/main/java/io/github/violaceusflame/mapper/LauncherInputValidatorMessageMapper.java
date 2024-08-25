package io.github.violaceusflame.mapper;

import io.github.violaceusflame.exception.ValidatorException;
import io.github.violaceusflame.validator.LauncherInputValidator.Result;

public class LauncherInputValidatorMessageMapper implements MessageMapper<ValidatorException> {
    @Override
    public String apply(ValidatorException e) {
        Result result = (Result) e.getValue();
        return Converter.getByResult(result);
    }

    private enum Converter {
        ALLOWED_ONLY_ONE_CHARACTER(Result.ALLOWED_ONLY_ONE_CHARACTER, "Ввести можно только один символ!"),
        ALLOWED_ONLY_DIGIT(Result.ALLOWED_ONLY_DIGIT, "Ввести можно только цифру, соответствующую пункту меню!");

        private final Result result;
        private final String message;

        Converter(Result result, String message) {
            this.result = result;
            this.message = message;
        }

        public static String getByResult(Result result) {
            Converter[] converters = values();

            for (Converter converter : converters) {
                if (converter.result == result) {
                    return converter.message;
                }
            }

            throw new IllegalArgumentException("Illegal result: " + result);
        }
    }
}
