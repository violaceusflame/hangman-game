package io.github.violaceusflame.display;

public class InfoDisplay implements Display {
    @Override
    public void showInfo(String message) {
        System.out.println(message);
    }

    @Override
    public void showError(String message) {
        showInfo(message);
    }
}
