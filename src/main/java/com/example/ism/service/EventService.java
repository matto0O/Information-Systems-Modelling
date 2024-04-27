package com.example.ism.service;

import com.example.ism.model.Event;
import com.example.ism.model.EventTag;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    public Event addEvent(Event event);
    public Event updateEvent(long id, Event event);
    public Event deleteEventById(long id);
    public Event detailedEvent(long id);
    public List<Event> findAllEvents();
    public List<Event> findEventsInTimeframe(LocalDate start, LocalDate end);
    public List<Event> findEventsByTags(List<EventTag> tags);
}
