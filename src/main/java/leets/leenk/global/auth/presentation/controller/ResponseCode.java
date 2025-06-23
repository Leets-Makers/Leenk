package leets.leenk.global.auth.presentation.controller;

import leets.leenk.global.common.response.ResponseCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode implements ResponseCodeInterface {

    INITIAL_LOGIN_SUCCESS(1002, HttpStatus.OK, "최초 로그인에 성공했습니다."),
    LOGIN_SUCCESS(1003, HttpStatus.OK, "로그인에 성공했습니다."),
    REFRESH_TOKEN_SUCCESS(1004, HttpStatus.OK, "토큰 재발급에 성공했습니다.");

    private final int code;
    private final HttpStatus status;
    private final String message;
}
