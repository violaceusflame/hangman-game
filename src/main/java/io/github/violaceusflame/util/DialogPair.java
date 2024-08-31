package io.github.violaceusflame.util;

import io.github.violaceusflame.dialogs.common.Dialog;

public class DialogPair {
    private final Dialog launcherDialog;
    private final Dialog sessionDialog;

    public static DialogPair of(Dialog launcherDialog, Dialog sessionDialog) {
        return new DialogPair(launcherDialog, sessionDialog);
    }

    public DialogPair(Dialog launcherDialog, Dialog sessionDialog) {
        this.launcherDialog = launcherDialog;
        this.sessionDialog = sessionDialog;
    }

    public Dialog getLauncherDialog() {
        return launcherDialog;
    }

    public Dialog getSessionDialog() {
        return sessionDialog;
    }
}
