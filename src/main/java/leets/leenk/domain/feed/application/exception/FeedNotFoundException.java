package leets.leenk.domain.feed.application.exception;

import leets.leenk.global.common.exception.BaseException;

public class FeedNotFoundException extends BaseException {
    public FeedNotFoundException() {
        super(ErrorCode.FEED_NOT_FOUND);
    }
}
