package it.epicode.Events.businesslayer.services.impl;

import it.epicode.Events.businesslayer.services.dto.AttendanceDTO;
import it.epicode.Events.businesslayer.services.interfaces.AttendanceService;
import it.epicode.Events.businesslayer.services.interfaces.CRUDService;
import it.epicode.Events.datalayer.entities.Attendance;
import it.epicode.Events.datalayer.repositories.AttendanceRepository;
import it.epicode.Events.datalayer.repositories.EventRepository;
import it.epicode.Events.datalayer.repositories.UserRepository;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.ExiperedEventException;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.NoAvailableSeatsException;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.NotFoundException;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.alreadyBookedBySameUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service("attendance")
@Slf4j
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceRepository attendance;

    @Autowired
    UserRepository user;

    @Autowired
    EventRepository event;


    @Override
    public Page<Attendance> getAll(Pageable p) {
        return attendance.findAll(p);
    }

    @Override
    public Attendance getById(Long id) {
        return attendance.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @Override
    public Attendance save(AttendanceDTO e) {
        var participant = user.findById(e.getUser_id()).orElseThrow(()-> new NotFoundException(e.getUser_id()));
        var eventEntity = event.findById(e.getEvent_id()).orElseThrow(()-> new NotFoundException(e.getEvent_id()));
        var availableSeat = attendance.findByEventId(e.getEvent_id());
        var alreadyBookedBySameUser = attendance.findByUserIdAndEventId(e.getUser_id(), e.getEvent_id());
        if (availableSeat.size() < eventEntity.getMaxParticipants()) {

            if (eventEntity.getDate().isAfter(LocalDate.now()) || eventEntity.getDate().isEqual(LocalDate.now())) {

                if (alreadyBookedBySameUser.isEmpty()){
                    return attendance.save(Attendance.builder()
                            .withUser(participant)
                            .withEvent(eventEntity)
                            .build());
                }else {
                    throw new alreadyBookedBySameUserException(e.getUser_id(), e.getEvent_id());
                }

            }else {
                throw new ExiperedEventException(eventEntity);
            }

        } else {
            throw new NoAvailableSeatsException(eventEntity);
        }
    }

    @Override
    public Attendance update(Long id, Attendance attendanceModified) {
        return null; //NOT IMPLEMENTED - NOT NECESSARY
    }

    @Override
    public Attendance delete(Long id) {
        try {
            var a = this.getById(id);
            attendance.delete(a);
            return a;
        } catch (NoSuchElementException e) {
            log.error(String.format("Cannot find attendance with id = %s", id), e);
            throw new RuntimeException("Cannot find attendance...");
        } catch (Exception e) {
            log.error(String.format("Error deleting attendance with id = %s", id), e);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Attendance> getAllByUserId(Long id) {
        var u = user.findById(id).orElseThrow(()-> new NotFoundException(id));
        return attendance.findByUserId(id);
    }
}
