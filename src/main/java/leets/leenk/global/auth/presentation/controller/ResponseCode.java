package leets.leenk.global.auth.presentation.controller;

import leets.leenk.global.common.response.ResponseCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode implements ResponseCodeInterface {

    LOGIN_SUCCESS(1003, HttpStatus.OK, "로그인에 성공했습니다.");

    private final int code;
    private final HttpStatus status;
    private final String message;
}
