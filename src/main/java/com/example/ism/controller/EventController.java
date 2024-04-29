package com.example.ism.controller;

import com.example.api.EventApi;
import com.example.ism.model.Event;
import com.example.ism.model.EventTag;
import com.example.ism.service.EventService;
import com.example.ism.service.OrganizerService;
import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.*;

@RestController
public class EventController implements EventApi {

    @Autowired
    EventService eventService;

    @Autowired
    OrganizerService organizerService;

    @Override
    public ResponseEntity<EventDTO> addEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());
        event.setDate(eventDTO.getDate().toLocalDate());
        Set<EventTag> tags = new HashSet<>();
        for (EventTagDTO tag : eventDTO.getEventTags()) {
            tags.add(new EventTag(tag.getId(), tag.getName()));
        }
        event.setTags(tags);
        event.setMapPath(eventDTO.getMapPath());
        event.setOrganizer(organizerService.findOrganizerById(eventDTO.getOrganizerId()));

        eventService.addEvent(event);
        return new ResponseEntity<>(eventDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteEvent(Long eventId) {
        if(eventService.detailedEvent(eventId) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        eventService.deleteEventById(eventId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EventDTO>> findAllEvents() {
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : eventService.findAllEvents()) {
            EventDTO eventDTO = new EventDTO();
            eventDTO.setId(event.getId());
            eventDTO.setName(event.getName());
            eventDTO.setDate(OffsetDateTime.from(event.getDate()));
            List<EventTagDTO> tags = new ArrayList<>();
            for (EventTag tag : event.getTags()) {
                EventTagDTO tagDTO = new EventTagDTO();
                tagDTO.setId(tag.getId());
                tagDTO.setName(tag.getName());
                tags.add(tagDTO);
            }
            eventDTO.setEventTags(tags);
            eventDTO.setMapPath(event.getMapPath());
            eventDTO.setOrganizerId(event.getOrganizer().getId());
            eventDTOs.add(eventDTO);
        }
        return new ResponseEntity<>(eventDTOs, HttpStatus.OK);
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
        Event event = eventService.detailedEvent(eventId);
        if(event == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setDate(OffsetDateTime.from(event.getDate()));
        List<EventTagDTO> tags = new ArrayList<>();
        for (EventTag tag : event.getTags()) {
            EventTagDTO tagDTO = new EventTagDTO();
            tagDTO.setId(tag.getId());
            tagDTO.setName(tag.getName());
            tags.add(tagDTO);
        }
        eventDTO.setEventTags(tags);
        eventDTO.setMapPath(event.getMapPath());
        eventDTO.setOrganizerId(event.getOrganizer().getId());
        return new ResponseEntity<>(eventDTO, HttpStatus.OK);
    }
}
