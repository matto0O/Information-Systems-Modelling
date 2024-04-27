package com.example.ism.service;

import com.example.ism.model.EventTag;

import java.util.List;

public interface EventTagService {
    public EventTag addEventTag(EventTag eventTag);
    public EventTag deleteEventTagById(long id);
    public List<EventTag> findAllEventTags();
}
