package it.epicode.booking.exceptions;

import it.epicode.booking.entities.User;

public class UserHasAlreadyABookedWorkStationInThisDay extends RuntimeException{
    public UserHasAlreadyABookedWorkStationInThisDay(User user){
        super("User " + user + "has already booked a workstation for this day");
    }

}
