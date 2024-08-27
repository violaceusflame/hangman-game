package io.github.violaceusflame.display;

public class InfoDisplay implements Display {
    @Override
    public void showInfo(String info) {
        System.out.println(info);
    }

    @Override
    public void showError(String errorInfo) {
        showInfo(errorInfo);
    }
}
