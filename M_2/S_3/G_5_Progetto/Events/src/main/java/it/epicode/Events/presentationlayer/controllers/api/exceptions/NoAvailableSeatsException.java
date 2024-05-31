package it.epicode.Events.presentationlayer.controllers.api.exceptions;


import it.epicode.Events.datalayer.entities.Event;

import java.io.Serial;

public class NoAvailableSeatsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public int key;

    public NoAvailableSeatsException(Event e) {
        this("Event " + e.getId() + " with max participants " + e.getMaxParticipants() + " has no seats available", e.getMaxParticipants());
    }

    public NoAvailableSeatsException(String message, int key) {
        super(message);
        this.key = key;
    }

}
