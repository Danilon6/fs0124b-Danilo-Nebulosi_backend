package it.epicode.DevicesManagment.controllers.exceptions;

import java.io.Serial;

public class AlreadyAssignedException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public AlreadyAssignedException(String message){
        super(message);
    }
}
