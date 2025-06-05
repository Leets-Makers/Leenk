package leets.leenk.global.auth.application.exception;

import leets.leenk.global.common.exception.BaseException;

public class UnRegisterUserException extends BaseException {
    public UnRegisterUserException() {
        super(ErrorCode.UN_REGISTER);
    }
}
