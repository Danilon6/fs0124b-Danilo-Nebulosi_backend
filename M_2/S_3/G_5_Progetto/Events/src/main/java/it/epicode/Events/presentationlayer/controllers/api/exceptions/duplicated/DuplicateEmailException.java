package it.epicode.Events.presentationlayer.controllers.api.exceptions.duplicated;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class DuplicateEmailException extends DuplicateKeyException{

    @Serial
    private static final long serialVersionUID = 1L;


    public DuplicateEmailException(String key) {
        super(key, HttpStatus.BAD_REQUEST, "Email duplicata");
    }
}
