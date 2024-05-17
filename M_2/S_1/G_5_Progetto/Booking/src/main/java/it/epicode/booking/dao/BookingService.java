package it.epicode.booking.dao;

import it.epicode.booking.exceptions.NoAvailabeSeatsException;
import it.epicode.booking.entities.Booking;
import it.epicode.booking.exceptions.UserHasAlreadyBookedAWorkStationInThisDay;
import it.epicode.booking.repositories.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class BookingService implements CRUDDao<Booking>{

    @Autowired
    BookingRepository booking;

    @Override
    public void create(Booking e) {
        var workStationBookingList = booking.findByWorkStationAndDate(e.getWorkStation(), e.getDate());
        var userBooking = booking.findByUserAndDate(e.getUser(), e.getDate());
        var w = e.getWorkStation();
        if (userBooking.isEmpty())
        {
            if (workStationBookingList.size() < w.getMax_seats()) {
                booking.save(e);
            } else {
                throw new NoAvailabeSeatsException(w.getMax_seats());
            }

        } else {
            throw new UserHasAlreadyBookedAWorkStationInThisDay(e.getUser());
        }

    }

    @Override
    public void update(Booking e) {
        booking.save(e);
    }

    @Override
    public void delete(Long id) {
        booking.deleteById(id);
    }

    @Override
    public Booking getById(Long id) {
        return booking.findById(id).orElse(null);
    }

    public Boolean isValid(Booking e, LocalDate today) {
        return e.getDate().isEqual(today);
    }
}
