package io.github.violaceusflame.messagecenter.exception;

public class UnableGetMessageCenterException extends RuntimeException {
    public UnableGetMessageCenterException(RuntimeException e) {
        super(e);
    }
}
