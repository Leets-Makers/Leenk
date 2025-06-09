package leets.leenk.global.auth.application.exception;

import leets.leenk.global.common.exception.BaseException;
import leets.leenk.global.common.exception.ErrorCode;

public class CustomJsonProcessingException extends BaseException {
    public CustomJsonProcessingException(String message) {
        super(ErrorCode.JSON_PROCESSING, ErrorCode.JSON_PROCESSING.getMessage() + " " + message);
    }
}
