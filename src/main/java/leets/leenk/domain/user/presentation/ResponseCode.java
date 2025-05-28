package leets.leenk.domain.user.presentation;

import leets.leenk.global.common.response.ResponseCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode implements ResponseCodeInterface {

    GET_MY_INFO(1100, HttpStatus.OK, "내 정보 조회에 성공했습니다."),
    GET_USER_INFO(1101, HttpStatus.OK, "다른 사용자 정보 조회에 성공했습니다.");

    private final int code;
    private final HttpStatus status;
    private final String message;
}
