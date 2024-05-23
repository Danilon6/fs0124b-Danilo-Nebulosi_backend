package it.epicode.restapiblog.config;

import it.epicode.restapiblog.controllers.exceptions.ApiValidationException;
import it.epicode.restapiblog.controllers.exceptions.DuplicateEmailException;
import it.epicode.restapiblog.controllers.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    public record ExceptionInfo (
            String message,
            String key) {}

    public record ExceptionValidationInfo (
            String field,
            String contentOfError
            ){}

    @ExceptionHandler(DuplicateEmailException.class)
    protected ResponseEntity<?> handleDuplicatedEmail(DuplicateEmailException e) {
        return new ResponseEntity<>(new ExceptionInfo(e.getMessage(), e.duplicateKey), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<?> handleNotFoundedElement(NotFoundException e) {
        return new ResponseEntity<>(new ExceptionInfo(e.getMessage(), String.valueOf(e.idNotFound)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiValidationException.class)
    protected ResponseEntity<?> handleApiValidationException(ApiValidationException e) {
        var body = e.errorsList.stream() //
                .filter(error -> error instanceof FieldError)//
                .map(error -> (FieldError) error) //
                .map(error -> new ExceptionValidationInfo(error.getField(), error.getDefaultMessage())//
                ).toList();

        return new ResponseEntity<>(body, e.status);
    }

}
