package it.epicode.booking.exceptions;

import it.epicode.booking.entities.WorkStation;

public class NoAvailabeSeatsException extends RuntimeException{
    public NoAvailabeSeatsException(WorkStation w) {
        super("Workstation " + w.getId() + " with max seats " + w.getMax_seats() + " has no seats available");
    }

}
