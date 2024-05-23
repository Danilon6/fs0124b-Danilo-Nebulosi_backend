package it.epicode.restapiblog.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.io.Serial;
import java.util.List;

public class BadRequestException extends ApiValidationException{

    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException(List<ObjectError> errors) {
        super(errors, HttpStatus.BAD_REQUEST);
    }
}
