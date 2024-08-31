package io.github.violaceusflame.dialogs.common.printer;

import io.github.violaceusflame.display.Display;

public class PrinterImpl implements Printer {
    private final Display display;

    public PrinterImpl(Display display) {
        this.display = display;
    }

    @Override
    public void printInfo(String message) {
        display.showInfo(message);
    }

    @Override
    public void printError(String message) {
        display.showError(message);
    }
}
