package leets.leenk.global.auth.application.exception;

import leets.leenk.global.common.exception.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeInterface {

    OAUTH_ERROR(2001, HttpStatus.UNAUTHORIZED, "인증에 실패했습니다."),
    UN_REGISTER(2002, HttpStatus.UNAUTHORIZED, "가입되지 않은 사용자입니다."),
    USER_IN_ACTIVE(2000, HttpStatus.FORBIDDEN, "가입 승인이 허가되지 않은 계정입니다.");

    private final int code;
    private final HttpStatus status;
    private final String message;
}
