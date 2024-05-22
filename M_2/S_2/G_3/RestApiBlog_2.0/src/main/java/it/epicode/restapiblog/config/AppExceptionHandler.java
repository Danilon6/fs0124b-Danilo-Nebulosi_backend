package it.epicode.restapiblog.config;

import it.epicode.restapiblog.services.exceptions.DuplicateEmailException;
import it.epicode.restapiblog.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @Data
    @AllArgsConstructor
    public static class ExceptionInfo{
        private String message;
        private String key;
    }

    @ExceptionHandler(DuplicateEmailException.class)
    protected ResponseEntity<?> handleDuplicatedEmail(DuplicateEmailException e) {
        return new ResponseEntity<>(new ExceptionInfo(e.getMessage(), e.duplicateKey), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<?> handleNotFoundedElement(NotFoundException e) {
        return new ResponseEntity<>(new ExceptionInfo(e.getMessage(), String.valueOf(e.idNotFound)), HttpStatus.NOT_FOUND);
    }

}
