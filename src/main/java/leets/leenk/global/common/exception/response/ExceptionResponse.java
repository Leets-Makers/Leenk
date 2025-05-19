package leets.leenk.global.common.exception.response;

import leets.leenk.global.common.exception.ErrorCode;

public record ExceptionResponse (
        int code,
        int status,
        String message
)
{
    public static ExceptionResponse of(ErrorCode errorCode) {
        return new ExceptionResponse(
                errorCode.getCode(),
                errorCode.getStatus().value(),
                errorCode.getMessage()
        );
    }
}
