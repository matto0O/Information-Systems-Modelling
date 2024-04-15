package com.example.ism;

import com.example.api.EventtagApi;
import com.example.model.EventTag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventTagController implements EventtagApi {

    @Override
    public ResponseEntity<EventTag> createEventTag(EventTag eventTag) {
        return EventtagApi.super.createEventTag(eventTag);
    }

    @Override
    public ResponseEntity<Void> deleteEventTag(Integer eventTagId) {
        return EventtagApi.super.deleteEventTag(eventTagId);
    }

    @Override
    public ResponseEntity<List<EventTag>> getAllEventTags() {
        return EventtagApi.super.getAllEventTags();
    }
}
