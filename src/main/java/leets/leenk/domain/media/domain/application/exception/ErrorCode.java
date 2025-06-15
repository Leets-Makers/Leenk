package leets.leenk.domain.media.domain.application.exception;

import leets.leenk.global.common.exception.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeInterface {

    MEDIA_NOT_FOUND(2500, HttpStatus.NOT_FOUND, "존재하지 않는 미디어입니다.");

    private final int code;
    private final HttpStatus status;
    private final String message;
}
