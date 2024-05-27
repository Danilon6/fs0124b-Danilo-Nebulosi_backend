package it.epicode.DevicesManagment.presentationlayer.controllers.exceptions;

import java.io.Serial;

public class AlreadyAssignedException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public AlreadyAssignedException(String message){
        super(message);
    }
}
