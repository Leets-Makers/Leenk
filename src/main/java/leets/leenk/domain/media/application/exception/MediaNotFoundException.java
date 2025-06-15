package leets.leenk.domain.media.application.exception;

import leets.leenk.global.common.exception.BaseException;

public class MediaNotFoundException extends BaseException {
    public MediaNotFoundException() {
        super(ErrorCode.MEDIA_NOT_FOUND);
    }
}
