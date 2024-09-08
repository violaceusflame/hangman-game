package io.github.violaceusflame.dialogs.dialogcenter;

import io.github.violaceusflame.constant.Language;
import io.github.violaceusflame.dialogs.dialogcenter.exception.UnableGetDialogCenterException;
import io.github.violaceusflame.util.inireader.IniReader;
import io.github.violaceusflame.util.inireader.exception.IniValueNotFoundException;
import io.github.violaceusflame.util.inireader.exception.OpenIniFileException;
import io.github.violaceusflame.util.inireader.exception.ReadIniFileException;

public class FileDialogCenter implements DialogCenter {
    private final IniReader iniReader;

    public FileDialogCenter(String directory, String filenameTemplate, Language language) {
        String languageName = language.name().toLowerCase();
        String filename = filenameTemplate.formatted(languageName);
        String iniFilename = String.format("%s/%s", directory.formatted(languageName), filename);
        this.iniReader = getIniReader(iniFilename);
    }

    private IniReader getIniReader(String iniFilename) {
        try {
            return new IniReader(iniFilename);
        } catch (OpenIniFileException | ReadIniFileException e) {
            throw new UnableGetDialogCenterException(e);
        }
    }

    @Override
    public String get(String section, String key) {
        try {
            return iniReader.getValue(section, key);
        } catch (IniValueNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
