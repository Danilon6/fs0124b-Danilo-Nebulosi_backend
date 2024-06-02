package it.epicode.Events.presentationlayer.controllers.api.exceptions;

import java.io.Serial;

public class alreadyBookedBySameUserException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public final Long user_id;
    public final Long event_id;

    public alreadyBookedBySameUserException(Long user_id, Long event_id) {
        super("l'utente con id: " + user_id + " ha già una prenotazione per l'evento con id: " + event_id);
        this.user_id = user_id;
        this.event_id = event_id;
    }
}
