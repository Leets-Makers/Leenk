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
    public ResponseEntity<ExceptionResponse<Void>> handleException(BaseException e) {
        ErrorCode errorCode = e.getErrorCode();
        ExceptionResponse<Void> body = ExceptionResponse.of(errorCode);

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse<List<ValidErrorResponse>>> handleValidation(MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.INVALID_ARGUMENT;
        List<ValidErrorResponse> errors = e.getBindingResult()
                .getFieldErrors().stream()
                .map(fe -> ValidErrorResponse.of(
                        fe.getField(),
                        fe.getDefaultMessage(),
                        fe.getRejectedValue()
                ))
                .toList();
        ExceptionResponse<List<ValidErrorResponse>> body =
                ExceptionResponse.of(errorCode, errors);

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse<Void>> handleIllegalArgument() {
        ErrorCode errorCode = ErrorCode.INVALID_ARGUMENT;
        ExceptionResponse<Void> body = ExceptionResponse.of(errorCode);

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionResponse<Void>> handleNoResourceFound() {
        ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;
        ExceptionResponse<Void> body = ExceptionResponse.of(errorCode);

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse<Void>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        ExceptionResponse<Void> body = ExceptionResponse.of(errorCode);

        return ResponseEntity
                .status(e.getStatusCode().value())
                .body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse<Void>> handleMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getMostSpecificCause();
        if (cause instanceof BaseException be) {
            ErrorCode errorCode = be.getErrorCode();
            ExceptionResponse<Void> body = ExceptionResponse.of(errorCode);

            return ResponseEntity
                    .status(errorCode.getStatus())
                    .body(body);
        }

        ErrorCode errorCode = ErrorCode.JSON_PARSE_ERROR;
        ExceptionResponse<Void> body = ExceptionResponse.of(errorCode);

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse<Void>> handleAll() {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ExceptionResponse<Void> body = ExceptionResponse.of(errorCode);

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(body);
    }
}
