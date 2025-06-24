package leets.leenk.domain.notification.application.exception;

import leets.leenk.global.common.exception.BaseException;

public class InvalidNotificationContentTypeException extends BaseException {
    public InvalidNotificationContentTypeException() {
        super(ErrorCode.INVALID_NOTIFICATION_CONTENT_TYPE_EXCEPTION);
    }
}
