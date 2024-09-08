package io.github.violaceusflame.display;

import static io.github.violaceusflame.display.AnsiTextColor.RESET;

public class ColorizedInfoDisplay extends InfoDisplay implements Display {
    private final AnsiTextColor textColor;

    public ColorizedInfoDisplay(AnsiTextColor textColor) {
        this.textColor = textColor;
    }

    @Override
    public void show(String text) {
        String colorizedText = getColorizedText(text, textColor);
        super.show(colorizedText);
    }

    private String getColorizedText(String info, AnsiTextColor textColor) {
        return textColor.escapeCode + info + RESET.escapeCode;
    }
}
