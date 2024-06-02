package it.epicode.Events.presentationlayer.controllers.api;

import it.epicode.Events.businesslayer.services.dto.AttendanceDTO;
import it.epicode.Events.businesslayer.services.dto.EventDTO;
import it.epicode.Events.businesslayer.services.interfaces.CRUDService;
import it.epicode.Events.datalayer.entities.Attendance;
import it.epicode.Events.datalayer.entities.Event;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.ApiValidationException;
import it.epicode.Events.presentationlayer.controllers.api.models.AttendanceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/attendance")
public class AttendanceController {

    @Autowired
    CRUDService<Attendance, AttendanceDTO> attendance;


    @GetMapping
    public ResponseEntity<Page<Attendance>> getAll (Pageable p){
        var allAttendance = attendance.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allAttendance.getTotalElements()));
        return new ResponseEntity<>(allAttendance, headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Attendance> getAttendance (@PathVariable Long id){
        var a = attendance.getById(id);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Attendance> addAttendance (
            @RequestBody @Validated AttendanceModel model,
            BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
        var a = attendance.save(AttendanceDTO.builder()
                        .withUser_id(model.user_id())
                        .withEvent_id(model.event_id())
                        .build());

        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Attendance> deleteAttendance (
            @PathVariable Long id
    ) {
        var a = attendance.delete(id);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

}
