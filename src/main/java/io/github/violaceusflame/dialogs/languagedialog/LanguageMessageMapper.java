package io.github.violaceusflame.dialogs.languagedialog;

import io.github.violaceusflame.dialogs.common.messagemapper.AbstractOptionMessageMapper;

public class LanguageMessageMapper extends AbstractOptionMessageMapper {
    private static final String INVALID_LANGUAGE = "Invalid language";

    public LanguageMessageMapper() {
        super((section, key) -> "Invalid language");
    }

    @Override
    protected String messageInputDoesNotMatchWithOptions() {
        return INVALID_LANGUAGE;
    }
}
