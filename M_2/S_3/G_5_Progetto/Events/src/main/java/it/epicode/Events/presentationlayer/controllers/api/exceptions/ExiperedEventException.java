package it.epicode.Events.presentationlayer.controllers.api.exceptions;

import it.epicode.Events.datalayer.entities.Event;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.io.Serial;
import java.time.LocalDate;

public class ExiperedEventException extends  RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public final LocalDate key;

    public ExiperedEventException(Event e){
        this("Non è possibile partecipare ad un evento scaduto", e.getDate());
    }

    public ExiperedEventException(String message, LocalDate key){
        super(message);
        this.key = key;
    }
}
