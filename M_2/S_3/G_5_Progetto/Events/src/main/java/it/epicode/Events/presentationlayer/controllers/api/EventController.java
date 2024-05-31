package it.epicode.Events.presentationlayer.controllers.api;

import it.epicode.Events.businesslayer.services.dto.EventDTO;
import it.epicode.Events.businesslayer.services.impl.EventServiceImpl;
import it.epicode.Events.businesslayer.services.interfaces.CRUDService;
import it.epicode.Events.datalayer.entities.Event;

import it.epicode.Events.presentationlayer.controllers.api.exceptions.ApiValidationException;
import it.epicode.Events.presentationlayer.controllers.api.models.EventModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/event")
public class EventController {
    @Autowired
    //@Qualifier("event")
    CRUDService<Event, EventDTO> event;

    @GetMapping
    public ResponseEntity<Page<Event>> getEvents (Pageable p) {
        var allEvents = event.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allEvents.getTotalElements()));
        return new ResponseEntity<>(allEvents, headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Event> getEvent (@PathVariable Long id) {
        var e = event.getById(id);
        return new ResponseEntity<>(e, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Event> addEvent (@RequestBody @Validated EventModel model, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
        var e = event.save(
                EventDTO.builder()
                        .withTitle(model.title())
                        .withDescription(model.description())
                        .withPlace(model.place())
                        .withMaxPrticipants(model.maxParticipants())
                        .build()
        );
          return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Event> updateEvent (
            @PathVariable Long id,
            @RequestBody Event eventModified
    ) {
        var e = event.update(id, eventModified);
        return new ResponseEntity<>(e, null, HttpStatus.OK);
    }

}
