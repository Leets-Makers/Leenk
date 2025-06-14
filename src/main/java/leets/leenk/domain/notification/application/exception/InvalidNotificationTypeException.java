package leets.leenk.domain.notification.application.exception;

import leets.leenk.global.common.exception.BaseException;

public class InvalidNotificationTypeException extends BaseException {
	public InvalidNotificationTypeException() {
		super(ErrorCode.INVALID_NOTIFICATION_TYPE_EXCEPTION);
	}
}
