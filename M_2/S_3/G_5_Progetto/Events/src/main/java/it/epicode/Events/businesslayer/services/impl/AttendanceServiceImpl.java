package it.epicode.Events.businesslayer.services.impl;

import it.epicode.Events.businesslayer.services.dto.AttendanceDTO;
import it.epicode.Events.businesslayer.services.interfaces.CRUDService;
import it.epicode.Events.datalayer.entities.Attendance;
import it.epicode.Events.datalayer.repositories.AttendanceRepository;
import it.epicode.Events.datalayer.repositories.EventRepository;
import it.epicode.Events.datalayer.repositories.UserRepository;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.ExiperedEventException;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.NoAvailableSeatsException;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.NotFoundException;
import it.epicode.Events.presentationlayer.controllers.utility.EntitiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service("attendance")
@Slf4j
public class AttendanceServiceImpl implements CRUDService<Attendance, AttendanceDTO> {

    @Autowired
    AttendanceRepository attendance;

    @Autowired
    UserRepository user;

    @Autowired
    EventRepository event;

    @Autowired
    EntitiesUtils utils;

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
        var avaliableSeat = attendance.findByEventId(e.getEvent_id());

        if (avaliableSeat.size() < eventEntity.getMaxParticipants()) {

            if (eventEntity.getDate().isAfter(LocalDate.now()) || eventEntity.getDate().isEqual(LocalDate.now())) {
                return attendance.save(Attendance.builder()
                        .withUser(participant)
                        .withEvent(eventEntity)
                        .build());
            }else {
                throw new ExiperedEventException(eventEntity);
            }

        } else {
            throw new NoAvailableSeatsException(eventEntity);
        }
    }

    @Override
    public Attendance update(Long id, Attendance attendanceModified) {
        try {
            var a = attendance.findById(id).orElseThrow(()-> new NotFoundException(id));
            utils.copy(attendanceModified, a);
            return attendance.save(a);
        } catch (NoSuchElementException e) {
            log.error(String.format("Cannot find attendance with id = %s", id), e);
            throw new RuntimeException("Cannot find attendance...");
        } catch (Exception e) {
            log.error(String.format("Error updating attendance with id = %s", id), e);
            throw new RuntimeException();
        }
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
}
