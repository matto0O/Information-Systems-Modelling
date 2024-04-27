package com.example.ism.serviceimpl;

import com.example.ism.model.EventTag;
import com.example.ism.repository.EventTagRepository;
import com.example.ism.service.EventTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventTagServiceImpl implements EventTagService {

    @Autowired
    private EventTagRepository eventTagRepository;

    @Override
    public EventTag addEventTag(EventTag eventTag) {
        return eventTagRepository.save(eventTag);
    }

    @Override
    public EventTag deleteEventTagById(long id) {
        Optional<EventTag> c = eventTagRepository.findById(id);
        if(c.isPresent()){
            eventTagRepository.deleteById(id);
            return c.get();
        }
        return null;
    }

    @Override
    public List<EventTag> findAllEventTags() {
        return eventTagRepository.findAll();
    }
}
