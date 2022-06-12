package me.eduspace.exceptions.handler;

import me.eduspace.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler({ItemNotFoundException.class,
            AppBadRequestException.class, ItemAlreadyExistsException.class})
    public ResponseEntity<?> handleBadRequestException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({AppForbiddenException.class})
    public ResponseEntity<?> handleForbidden(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler({TimeExpiredException.class})
    public ResponseEntity<?> handeGone(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.GONE).body(e.getMessage());
    }
}
