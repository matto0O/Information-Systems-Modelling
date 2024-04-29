package com.example.ism.serviceimpl;

import com.example.ism.model.Event;
import com.example.ism.model.EventTag;
import com.example.ism.repository.EventRepository;
import com.example.ism.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(long id, Event event) {
        Optional<Event> e = eventRepository.findById(id);
        if (e.isPresent()) {
            Event existingEvent = e.get();
            existingEvent.setName(event.getName());
            existingEvent.setDate(event.getDate());
            existingEvent.setTags(event.getTags());
            existingEvent.setOrganizer(event.getOrganizer());
            existingEvent.setMapPath(event.getMapPath());
            return eventRepository.save(existingEvent);
        }
        event.setId(id);
        return eventRepository.save(event);
    }

    @Override
    public Event deleteEventById(long id) {
        Optional<Event> e = eventRepository.findById(id);
        if (e.isPresent()) {
            Event existingEvent = e.get();
            eventRepository.deleteById(id);
            return existingEvent;
        }
        return null;
    }

    @Override
    public Event detailedEvent(long id) {
        return eventRepository.getReferenceById(id);
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findEventsInTimeframe(LocalDate start, LocalDate end) {
//        return eventRepository.findAllBetweenDates(start, end);
        return null;
    }

    @Override
    public List<Event> findEventsByTags(List<EventTag> tags) {
        return eventRepository.findAllByTags(tags);
    }
}
