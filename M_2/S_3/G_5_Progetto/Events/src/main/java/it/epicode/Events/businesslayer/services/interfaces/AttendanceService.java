package it.epicode.Events.businesslayer.services.interfaces;

import it.epicode.Events.businesslayer.services.dto.AttendanceDTO;
import it.epicode.Events.datalayer.entities.Attendance;

import java.util.List;

public interface AttendanceService extends CRUDService<Attendance, AttendanceDTO>{
    List<Attendance> getAllByUserId(Long id);
}
