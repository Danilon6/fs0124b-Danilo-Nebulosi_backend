package it.epicode.DevicesManagment.presentationlayer.controllers.exceptions.duplicated;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class DuplicateUsernameException extends DuplicateKeyException{

    @Serial
    private static final long serialVersionUID = 1L;


    public DuplicateUsernameException(String key) {
        super(key, HttpStatus.BAD_REQUEST, "Username duplicato");
    }
}
