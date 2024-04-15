package com.example.ism;

import com.example.api.EventApi;
import com.example.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController implements EventApi {
    private static long id = 0;
    private final List<Event> le = new ArrayList<>();
    private final List<EventWithoutTags> lewt = new ArrayList<>();

    @Override
    public ResponseEntity<Event> addEvent(Event event) {
        event.setId(++id);
        le.add(event);
        EventWithoutTags eventWithoutTags = new EventWithoutTags();
        eventWithoutTags.setId(event.getId());
        lewt.add(eventWithoutTags);
        return ResponseEntity.of(Optional.of(event));
    }

    @Override
    public ResponseEntity<Void> deleteEvent(Long eventId) {
        Optional<Event> result = le.stream().findFirst().filter(
                event -> event.getId().equals(eventId)
        );

        if (result.isPresent()) {
            Event obj = result.get();
            le.remove(obj);
            Optional<EventWithoutTags> resultWithoutTags = lewt.stream().findFirst().filter(
                    event -> event.getId().equals(eventId)
            );
            if (resultWithoutTags.isPresent()) {
                EventWithoutTags objwt = resultWithoutTags.get();
                lewt.remove(objwt);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Event>> findAllEvents() {
        return ResponseEntity.of(Optional.of(le));
    }

    @Override
    public ResponseEntity<List<Event>> findEventsByEventTags(List<Integer> tags) {
        return ResponseEntity.of(Optional.of(le.stream().filter(
                event -> {
                    for (EventTag tag : event.getEventTags()) {
                        if (tags.contains(Math.toIntExact(tag.getId()))) {
                            return true;
                        }
                    }
                    return false;
                }
        ).toList()));
    }

    @Override
    public ResponseEntity<List<Event>> findInTimeframe(OffsetDateTime before, OffsetDateTime after) {
        return ResponseEntity.of(Optional.of(le.stream().filter(
                event -> event.getDate().isAfter(before) && event.getDate().isBefore(after)
        ).toList()));
    }

    @Override
    public ResponseEntity<Event> getEventById(Long eventId) {
        return ResponseEntity.of(Optional.of(le.stream().findFirst().filter(
                event -> event.getId().equals(eventId)
        )).get());
    }

    @Override
    public ResponseEntity<EventWithoutTags> updateEvent(EventWithoutTags eventWithoutTags) {
        Optional<EventWithoutTags> result = lewt.stream().findFirst().filter(
                event -> event.getId().equals(eventWithoutTags.getId())
        );

        if (result.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        Optional<Event> resultE = le.stream().findFirst().filter(
                event -> event.getId().equals(eventWithoutTags.getId())
        );

        if (resultE.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        EventWithoutTags obj = result.get();
        Event objE = resultE.get();

        obj.setDate(eventWithoutTags.getDate());
        objE.setDate(eventWithoutTags.getDate());
        obj.setMapPath(eventWithoutTags.getMapPath());
        objE.setMapPath(eventWithoutTags.getMapPath());
        obj.setName(eventWithoutTags.getName());
        objE.setName(eventWithoutTags.getName());

        return ResponseEntity.of(Optional.of(obj));
    }

//    @Override
//    public ResponseEntity<ModelApiResponse> uploadFile(Long eventId, MultipartFile body) {
//        return EventApi.super.uploadFile(eventId, body);
//    }
}
