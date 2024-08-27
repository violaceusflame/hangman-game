package io.github.violaceusflame.constant;

public enum AnsiTextColor {
    CYAN("\u001B[36m"),
    RED("\u001B[91m"),
    RESET("\u001B[0m");

    public final String escapeCode;

    AnsiTextColor(String escapeCode) {
        this.escapeCode = escapeCode;
    }
}
