package it.epicode.booking.exceptions;

import it.epicode.booking.entities.Booking;
import it.epicode.booking.entities.User;

public class UserHasAlreadyBookedAWorkStationInThisDay extends RuntimeException{
    public UserHasAlreadyBookedAWorkStationInThisDay(User user){
        super("User " + user + "has already booked for this day");
    }

}
