package pers.dog.project.manager.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pers.dog.project.manager.exception.UnauthorizedException;

/**
 * 异常控制处理
 *
 * @author 废柴 2020/12/24 17:24
 */
@ControllerAdvice
public class ExceptionHandleProcessor {
    public static class ExceptionResponseBody {
        private String message;

        public String getMessage() {
            return message;
        }

        public ExceptionResponseBody setMessage(String message) {
            this.message = message;
            return this;
        }
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionResponseBody> handleException() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponseBody()
                        .setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseBody> handleException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponseBody()
                        .setMessage(e.getMessage()));
    }
}
