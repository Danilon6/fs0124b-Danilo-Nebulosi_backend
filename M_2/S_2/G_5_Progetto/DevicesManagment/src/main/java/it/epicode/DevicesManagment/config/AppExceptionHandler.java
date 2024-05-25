package it.epicode.DevicesManagment.config;

import it.epicode.DevicesManagment.controllers.exceptions.*;
//import it.epicode.restapiblog.controllers.exceptions.ApiValidationException;
//import it.epicode.restapiblog.controllers.exceptions.DuplicateEmailException;
//import it.epicode.restapiblog.controllers.exceptions.NotFoundException;
import it.epicode.DevicesManagment.controllers.exceptions.duplicated.DuplicateEmailException;
import it.epicode.DevicesManagment.controllers.exceptions.duplicated.DuplicateKeyException;
import it.epicode.DevicesManagment.controllers.exceptions.duplicated.DuplicateSerialNumberException;
import it.epicode.DevicesManagment.controllers.exceptions.duplicated.DuplicateUsernameException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    @ExceptionHandler(DuplicateKeyException.class)
    protected ResponseEntity<?> handleDuplicatedKey(DuplicateKeyException e) {
        return new ResponseEntity<>(new ExceptionInfo(e.getMessage(), e.key), e.status);
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
