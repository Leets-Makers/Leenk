package leets.leenk.domain.feed.application.exception;

import leets.leenk.global.common.exception.BaseException;

public class FeedDeleteNotAllowedException extends BaseException {
    public FeedDeleteNotAllowedException() {
        super(ErrorCode.FEED_DELETE_NOT_ALLOWED);
    }
}
