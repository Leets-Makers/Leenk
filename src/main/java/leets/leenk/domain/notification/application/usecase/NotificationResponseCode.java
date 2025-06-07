package leets.leenk.domain.notification.application.usecase;

import org.springframework.http.HttpStatus;

import leets.leenk.global.common.response.ResponseCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NotificationResponseCode implements ResponseCodeInterface {

	NOTIFICATION_READ_SUCCESS(1300, HttpStatus.OK, "알림 조회에 성공했습니다.");

	private final int code;
	private final HttpStatus status;
	private final String message;
}
