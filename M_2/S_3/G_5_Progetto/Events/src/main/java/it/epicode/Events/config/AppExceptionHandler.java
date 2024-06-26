package it.epicode.Events.config;

import it.epicode.Events.presentationlayer.controllers.api.exceptions.*;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.duplicated.DuplicateKeyException;
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

    public record ExceptionInvalidLoginInfo (
            String message,
            String username,
            String password
            ){}

    public record ExceptionAlreadyBookedInfo (
            String message,
            Long user_id,
            Long event_id
    ) {}

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


    @ExceptionHandler(InvalidLoginException.class)
    protected ResponseEntity<?> handleInvalidLoginException(InvalidLoginException e) {
        return new ResponseEntity<>(new ExceptionInvalidLoginInfo(e.username, e.password, e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoAvailableSeatsException.class)
    protected ResponseEntity<?> handleNoAvailableSeats(NoAvailableSeatsException e) {
        return new ResponseEntity<>(new ExceptionInfo(e.getMessage(), String.valueOf(e.key)), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ExiperedEventException.class)
    protected ResponseEntity<?> handleExiperedEvent(ExiperedEventException e) {
        return new ResponseEntity<>(new ExceptionInfo(e.getMessage(), String.valueOf(e.key)), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(alreadyBookedBySameUserException.class)
    protected ResponseEntity<?> handleAlreadyBookedBySameUser(alreadyBookedBySameUserException e) {
        return new ResponseEntity<>(new ExceptionAlreadyBookedInfo(e.getMessage(), e.user_id, e.event_id), HttpStatus.NOT_ACCEPTABLE);
    }
}
