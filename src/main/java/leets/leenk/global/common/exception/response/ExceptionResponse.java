package leets.leenk.global.common.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import leets.leenk.global.common.exception.ErrorCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExceptionResponse<T> (
        int code,
        String message,
        T data
)
{
    public static ExceptionResponse<Void> of(ErrorCode errorCode) {
        return new ExceptionResponse<>(
                errorCode.getCode(),
                errorCode.getMessage(),
                null
        );
    }
    public static <T> ExceptionResponse<T> of(ErrorCode errorCode, T data) {
        return new ExceptionResponse<>(
                errorCode.getCode(),
                errorCode.getMessage(),
                data
        );
    }
}
