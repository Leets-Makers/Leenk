package leets.leenk.global.common.exception;

import leets.leenk.global.common.exception.response.ValidErrorResponse;
import leets.leenk.global.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<CommonResponse<Void>> handleException(BaseException e) {
        ErrorCodeInterface errorCode = e.getErrorCode();
        String errorMessage = e.getMessage();
        log.warn("구체로그: ", e);
        CommonResponse<Void> body = CommonResponse.error(errorCode, errorMessage);

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<List<ValidErrorResponse>>> handleValidation(MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.INVALID_ARGUMENT;
        log.warn("구체로그: ", e);

        List<ValidErrorResponse> errors = e.getBindingResult()
                .getFieldErrors().stream()
                .map(fe -> ValidErrorResponse.of(
                        fe.getField(),
                        fe.getDefaultMessage(),
                        fe.getRejectedValue()
                ))
                .toList();
        CommonResponse<List<ValidErrorResponse>> body =
                CommonResponse.error(errorCode, errors);

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CommonResponse<Void>> handleIllegalArgument(IllegalArgumentException e) {
        ErrorCode errorCode = ErrorCode.INVALID_ARGUMENT;
        CommonResponse<Void> body = CommonResponse.error(errorCode);
        log.warn("구체로그: ", e);


        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<CommonResponse<Void>> handleNoResourceFound() {
        ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;
        CommonResponse<Void> body = CommonResponse.error(errorCode);

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CommonResponse<Void>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        CommonResponse<Void> body = CommonResponse.error(errorCode);
        log.warn("구체로그: ", e);


        return ResponseEntity
                .status(e.getStatusCode().value())
                .body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CommonResponse<Void>> handleMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getMostSpecificCause();

        if (cause instanceof BaseException be) {
            ErrorCodeInterface errorCode = be.getErrorCode();
            CommonResponse<Void> body = CommonResponse.error(errorCode, ex.getMessage());

            return ResponseEntity
                    .status(errorCode.getStatus())
                    .body(body);
        }

        ErrorCode errorCode = ErrorCode.JSON_PARSE_ERROR;
        CommonResponse<Void> body = CommonResponse.error(errorCode, ex.getMessage());

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Void>> handleAll(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        CommonResponse<Void> body = CommonResponse.error(errorCode, e.getMessage());
        log.warn("구체로그: ", e);


        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }
}
