package it.epicode.booking.exceptions;

public class NoAvailabeSeatsException extends RuntimeException{
    public NoAvailabeSeatsException(int maxSeats) {
        super("Workstation with max seats " + maxSeats + " has no seats available");
    }

}
