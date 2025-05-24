package leets.leenk.global.common.response;

import leets.leenk.global.common.exception.ErrorCode;

public record CommonResponse<T> (
        int code,
        String message,
        T data
)
{
    public static CommonResponse<Void> success(String message) {
        return new CommonResponse<>(
                200,
                message,
                null
        );
    }
    public static <T> CommonResponse<T> success(String message, T data) {
        return new CommonResponse<>(
                200,
                message,
                data
        );
    }
    public static CommonResponse<Void> error(ErrorCode errorCode) {
        return new CommonResponse<>(
                errorCode.getCode(),
                errorCode.getMessage(),
                null
        );
    }
    public static <T> CommonResponse<T> error(ErrorCode errorCode, T data) {
        return new CommonResponse<>(
                errorCode.getCode(),
                errorCode.getMessage(),
                data
        );
    }
}
