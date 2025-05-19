package leets.leenk.global.common.exception;

import java.util.List;
import leets.leenk.global.common.exception.response.ExceptionResponse;
import leets.leenk.global.common.exception.response.ValidErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionResponse> handleException(BaseException e) {
        ErrorCode errorCode = e.getErrorCode();
        ExceptionResponse body = ExceptionResponse.of(errorCode);
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidErrorResponse>> handleValidation(MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.INVALID_ARGUMENT;
        List<ValidErrorResponse> errors = e.getBindingResult()
                .getFieldErrors().stream()
                .map(fe -> ValidErrorResponse.of(
                        fe.getField(),
                        fe.getDefaultMessage(),
                        fe.getRejectedValue()
                ))
                .toList();
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgument() {
        ErrorCode errorCode = ErrorCode.INVALID_ARGUMENT;
        ExceptionResponse body = ExceptionResponse.of(errorCode);
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoResourceFound() {
        ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;
        ExceptionResponse body = ExceptionResponse.of(errorCode);
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleMethodNotAllowed() {
        ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        ExceptionResponse body = ExceptionResponse.of(errorCode);
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getMostSpecificCause();
        if (cause instanceof BaseException be) {
            ErrorCode errorCode = be.getErrorCode();
            ExceptionResponse body = ExceptionResponse.of(errorCode);
            return ResponseEntity
                    .status(errorCode.getStatus())
                    .body(body);
        }

        ErrorCode errorCode = ErrorCode.JSON_PARSE_ERROR;
        ExceptionResponse body = ExceptionResponse.of(errorCode);
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAll() {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ExceptionResponse body = ExceptionResponse.of(errorCode);
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }
}
