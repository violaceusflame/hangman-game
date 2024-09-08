package io.github.violaceusflame.messagecenter;

import io.github.violaceusflame.constant.Language;
import io.github.violaceusflame.messagecenter.exception.MessageNotFoundException;
import io.github.violaceusflame.messagecenter.exception.UnableGetMessageCenterException;
import io.github.violaceusflame.util.inireader.IniReader;
import io.github.violaceusflame.util.inireader.exception.IniValueNotFoundException;
import io.github.violaceusflame.util.inireader.exception.OpenIniFileException;
import io.github.violaceusflame.util.inireader.exception.ReadIniFileException;

public class FileMessageCenter implements MessageCenter {
    private final IniReader iniReader;

    public FileMessageCenter(String directory, String filenameTemplate, Language language) {
        String languageName = language.name().toLowerCase();
        String filename = filenameTemplate.formatted(languageName);
        String iniFilename = String.format("%s/%s", directory.formatted(languageName), filename);
        this.iniReader = getIniReader(iniFilename);
    }

    private IniReader getIniReader(String iniFilename) {
        try {
            return new IniReader(iniFilename);
        } catch (OpenIniFileException | ReadIniFileException e) {
            throw new UnableGetMessageCenterException(e);
        }
    }

    @Override
    public String get(String section, String key) {
        try {
            return iniReader.getValue(section, key);
        } catch (IniValueNotFoundException e) {
            throw new MessageNotFoundException(e);
        }
    }
}
