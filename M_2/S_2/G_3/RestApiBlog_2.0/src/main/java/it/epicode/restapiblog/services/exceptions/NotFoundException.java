package it.epicode.restapiblog.services.exceptions;

import java.io.Serial;

public class NotFoundException extends AppException{

    @Serial
    private static final long serialVersionUID = 1L;

    public final Long idNotFound;

    public NotFoundException(Long id, String message){
        super(message);
        this.idNotFound = id;
    }

    public NotFoundException(Long id){
        this(id, "Element not found");
    }
}
