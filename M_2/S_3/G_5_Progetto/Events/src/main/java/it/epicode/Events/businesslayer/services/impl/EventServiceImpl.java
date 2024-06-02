package it.epicode.Events.businesslayer.services.impl;

import it.epicode.Events.businesslayer.services.dto.EventDTO;
import it.epicode.Events.businesslayer.services.interfaces.CRUDService;
import it.epicode.Events.datalayer.entities.Event;
import it.epicode.Events.datalayer.entities.enums.Place;
import it.epicode.Events.datalayer.repositories.EventRepository;
import it.epicode.Events.datalayer.repositories.UserRepository;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.NotFoundException;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.duplicated.DuplicateTitleException;
import it.epicode.Events.presentationlayer.controllers.api.models.EventModel;
import it.epicode.Events.presentationlayer.controllers.utility.EntitiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service("event")
@Slf4j
public class EventServiceImpl implements CRUDService<Event, EventDTO> {

    @Autowired
    EventRepository event;

    @Autowired
    UserRepository user;


    @Override
    public Page<Event> getAll(Pageable p) {
        return event.findAll(p);
    }

    @Override
    public Event getById(Long id) {
        return event.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @Override
    public Event save(EventDTO e) {
        var placeToEnum = Place.valueOf(e.getPlace());
        var duplicateTitle = event.findOneByTitle(e.getTitle());

        if (duplicateTitle.isPresent()) {
            throw new DuplicateTitleException(e.getTitle());
        }else {
            return event.save(Event.builder()
                    .withTitle(e.getTitle())
                    .withDescription(e.getDescription())
                    .withPlace(placeToEnum)
                    .withMaxParticipants(e.getMaxPrticipants())
                    .build());
        }

    }

    @Override
    public Event update(Long id, Event eventModified) {
        var toModify = this.getById(id);
        if (eventModified.getTitle() != null){
            toModify.setTitle(eventModified.getTitle());
        }
        if (eventModified.getDescription() != null){
            toModify.setTitle(eventModified.getTitle());
        }
        if (eventModified.getDate() != null){
            toModify.setDate(eventModified.getDate());
        }
        if (eventModified.getPlace() != null){
            toModify.setPlace(eventModified.getPlace());
        }
        if (eventModified.getMaxParticipants() != 0 ){
            toModify.setMaxParticipants(eventModified.getMaxParticipants());
        }
        return event.save(toModify);


//        try {
//            var e = event.findById(id).orElseThrow();
//            utils.copy(eventModified, e);
//            return event.save(e);
//        } catch (NoSuchElementException e) {
//            log.error(String.format("Cannot find event with id = %s", id), e);
//            throw new RuntimeException("Cannot find event...");
//        } catch (Exception e) {
//            log.error(String.format("Error updating event with id = %s", id), e);
//            throw new RuntimeException();
//        }



    }

    @Override
    public Event delete(Long id) {
        try {
            var e = this.getById(id);
            event.delete(e);
            return e;
        } catch (NoSuchElementException e) {
            log.error(String.format("Cannot find event with id = %s", id), e);
            throw new RuntimeException("Cannot find event...");
        } catch (Exception e) {
            log.error(String.format("Error deleting event with id = %s", id), e);
            throw new RuntimeException();
        }
    }
}
