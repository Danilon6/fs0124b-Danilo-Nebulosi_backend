package it.epicode.Events.presentationlayer.controllers.api.exceptions.duplicated;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class DuplicateSerialNumberException extends DuplicateKeyException{

    @Serial
    private static final long serialVersionUID = 1L;


    public DuplicateSerialNumberException(Long key) {
        super(String.valueOf(key), HttpStatus.NOT_ACCEPTABLE, "Seral Number duplicato");
    }
}
