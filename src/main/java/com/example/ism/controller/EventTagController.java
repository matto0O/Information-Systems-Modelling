package com.example.ism.controller;

import com.example.api.EventtagApi;
import com.example.ism.model.EventTag;
import com.example.ism.service.EventTagService;
import com.example.model.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EventTagController implements EventtagApi {

    @Autowired
    EventTagService eventTagService;

    @Override
    public ResponseEntity<EventTagDTO> createEventTag(EventTagDTO eventTagDTO) {
        EventTag eventTag = new EventTag();
        eventTag.setName(eventTagDTO.getName());
        eventTag.setId(eventTagDTO.getId());
        eventTagService.addEventTag(eventTag);

        return new ResponseEntity<>(eventTagDTO, org.springframework.http.HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteEventTag(Integer eventTagId) {
        if (eventTagService.deleteEventTagById(eventTagId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EventTagDTO>> getAllEventTags() {
        List<EventTag> eventTags = eventTagService.findAllEventTags();
        List<EventTagDTO> eventTagDTOs = new ArrayList<>();
        eventTags.forEach(
                eventTag -> {
                    EventTagDTO eventTagDTO = new EventTagDTO();
                    eventTagDTO.setId(eventTag.getId());
                    eventTagDTO.setName(eventTag.getName());
                    eventTagDTOs.add(eventTagDTO);
                }
        );

        return new ResponseEntity<>(eventTagDTOs, org.springframework.http.HttpStatus.OK);
    }
}
