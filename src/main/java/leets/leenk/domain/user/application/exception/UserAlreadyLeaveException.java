package leets.leenk.domain.user.application.exception;

import leets.leenk.global.common.exception.BaseException;

public class UserAlreadyLeaveException extends BaseException {
    public UserAlreadyLeaveException() {
        super(ErrorCode.USER_ALREADY_Leave);
    }
}
