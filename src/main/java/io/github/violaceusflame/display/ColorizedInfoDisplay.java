package io.github.violaceusflame.display;

import io.github.violaceusflame.constant.AnsiTextColor;

import static io.github.violaceusflame.constant.AnsiTextColor.RESET;

public class ColorizedInfoDisplay extends InfoDisplay implements Display {
    private final AnsiTextColor textColor;

    public ColorizedInfoDisplay(AnsiTextColor textColor) {
        this.textColor = textColor;
    }

    @Override
    public void showInfo(String info) {
        String colorizedText = getColorizedText(info, textColor);
        super.showInfo(colorizedText);
    }

    @Override
    public void showError(String errorInfo) {
        String colorizedText = getColorizedText(errorInfo, AnsiTextColor.RED);
        super.showError(colorizedText);
    }

    private String getColorizedText(String info, AnsiTextColor textColor) {
        return textColor.escapeCode + info + RESET.escapeCode;
    }
}
