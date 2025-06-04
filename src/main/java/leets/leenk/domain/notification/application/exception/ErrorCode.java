package leets.leenk.domain.notification.application.exception;

import org.springframework.http.HttpStatus;

import leets.leenk.global.common.exception.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeInterface {

	INVALID_NOTIFICATION_REQUEST_EXCEPTION(2201, HttpStatus.BAD_REQUEST,
		"알림을 보내기 위해 필요한 정보(제목, 내용 또는 디바이스 토큰)가 누락되었습니다.");

	private final int code;
	private final HttpStatus status;
	private final String message;
}

