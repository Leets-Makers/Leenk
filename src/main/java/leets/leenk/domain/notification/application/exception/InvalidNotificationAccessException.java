package leets.leenk.domain.notification.application.exception;

import leets.leenk.global.common.exception.BaseException;

public class InvalidNotificationAccessException extends BaseException {
    public InvalidNotificationAccessException() {
        super(ErrorCode.INVALID_NOTIFICATION_ACCESS);
    }
}
