package leets.leenk.global.auth.application.exception;

import leets.leenk.global.common.exception.BaseException;

public class RefreshTokenException extends BaseException {
    public RefreshTokenException() {
        super(ErrorCode.INVALID_REFRESH_TOKEN);
    }
}
