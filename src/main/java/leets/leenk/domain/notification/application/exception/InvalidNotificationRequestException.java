package leets.leenk.domain.notification.application.exception;

import leets.leenk.global.common.exception.BaseException;

public class InvalidNotificationRequestException extends BaseException {
	public InvalidNotificationRequestException() {
		super(ErrorCode.INVALID_NOTIFICATION_REQUEST_EXCEPTION);
	}
}
