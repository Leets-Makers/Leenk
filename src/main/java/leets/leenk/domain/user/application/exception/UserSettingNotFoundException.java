package leets.leenk.domain.user.application.exception;

import leets.leenk.global.common.exception.BaseException;

public class UserSettingNotFoundException extends BaseException {
    public UserSettingNotFoundException() {
        super(ErrorCode.USER_SETTING_NOT_FOUND);
    }
}
