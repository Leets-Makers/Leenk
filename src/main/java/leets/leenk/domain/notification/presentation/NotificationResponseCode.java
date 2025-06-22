package leets.leenk.domain.notification.presentation;

import org.springframework.http.HttpStatus;

import leets.leenk.global.common.response.ResponseCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NotificationResponseCode implements ResponseCodeInterface {

	NOTIFICATION_READ_SUCCESS(1300, HttpStatus.OK, "알림 조회에 성공했습니다."),
	NOTIFICATION_COUNT_READ_SUCCESS(1301, HttpStatus.OK, "알림 개수 조회에 성공했습니다."),
	NOTIFICATION_MARK_AS_READ_SUCCESS(1303, HttpStatus.OK, "알림을 읽음 처리했습니다.");

	private final int code;
	private final HttpStatus status;
	private final String message;
}
