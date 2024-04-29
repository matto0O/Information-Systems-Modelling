package com.example.ism.controller;

import com.example.api.EventApi;
import com.example.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController implements EventApi {
    private static long id = 0;
    private final List<EventDTO> le = new ArrayList<>();
    private final List<EventWithoutTagsDTO> lewt = new ArrayList<>();

    @Override
    public ResponseEntity<EventDTO> addEvent(EventDTO eventDTO) {
        eventDTO.setId(++id);
        le.add(eventDTO);
        EventWithoutTagsDTO eventWithoutTagsDTO = new EventWithoutTagsDTO();
        eventWithoutTagsDTO.setId(eventDTO.getId());
        lewt.add(eventWithoutTagsDTO);
        return ResponseEntity.of(Optional.of(eventDTO));
    }

    @Override
    public ResponseEntity<Void> deleteEvent(Long eventId) {
        Optional<EventDTO> result = le.stream().findFirst().filter(
                eventDTO -> eventDTO.getId().equals(eventId)
        );

        if (result.isPresent()) {
            EventDTO obj = result.get();
            le.remove(obj);
            Optional<EventWithoutTagsDTO> resultWithoutTags = lewt.stream().findFirst().filter(
                    event -> event.getId().equals(eventId)
            );
            if (resultWithoutTags.isPresent()) {
                EventWithoutTagsDTO objwt = resultWithoutTags.get();
                lewt.remove(objwt);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<EventDTO>> findAllEvents() {
        return ResponseEntity.of(Optional.of(le));
    }

    @Override
    public ResponseEntity<List<EventDTO>> findEventsByEventTags(List<Integer> tags) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<List<EventDTO>> findInTimeframe(OffsetDateTime before, OffsetDateTime after) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<EventDTO> getEventById(Long eventId) {
        return ResponseEntity.of(Optional.of(le.stream().findFirst().filter(
                eventDTO -> eventDTO.getId().equals(eventId)
        )).get());
    }
}
