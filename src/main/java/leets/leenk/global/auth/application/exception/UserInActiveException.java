package leets.leenk.global.auth.application.exception;

import leets.leenk.global.common.exception.BaseException;

public class UserInActiveException extends BaseException {
    public UserInActiveException(String message) {
        super(ErrorCode.USER_IN_ACTIVE, message);
    }
}
