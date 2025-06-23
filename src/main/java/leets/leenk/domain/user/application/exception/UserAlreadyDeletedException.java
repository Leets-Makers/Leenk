package leets.leenk.domain.user.application.exception;

import leets.leenk.global.common.exception.BaseException;

public class UserAlreadyDeletedException extends BaseException {
    public UserAlreadyDeletedException() {
        super(ErrorCode.USER_ALREADY_DELETED);
    }
}
