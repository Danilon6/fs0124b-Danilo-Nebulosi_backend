package it.epicode.booking.exceptions;

import it.epicode.booking.entities.WorkStation;

public class NoAvailableSeatsException extends RuntimeException{
    public NoAvailableSeatsException(WorkStation w) {
        super("Workstation " + w.getId() + " with max seats " + w.getMax_seats() + " has no seats available");
    }

}
