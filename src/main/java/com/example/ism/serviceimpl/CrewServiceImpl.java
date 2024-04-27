package com.example.ism.serviceimpl;

import com.example.ism.model.Crew;
import com.example.ism.model.CrewStatus;
import com.example.ism.repository.CrewRepository;
import com.example.ism.repository.EventRepository;
import com.example.ism.repository.VolunteerRepository;
import com.example.ism.service.CrewService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CrewServiceImpl implements CrewService {

    @Autowired
    private CrewRepository crewRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private VolunteerRepository volunteerRepository;

    @Override
    public Crew getCrewMember(Long volunteerId, Long eventId) {
        return crewRepository.findCrewByVolunteerIdAndEventId(volunteerId, eventId);
    }

    @Override
    public Crew joinCrew(Long volunteerId, Long eventId) {
        Crew newCrew = new Crew();
        newCrew.setCrewStatus(CrewStatus.AVAILABLE);
        newCrew.setEvent(eventRepository.getReferenceById(eventId));
        newCrew.setVolunteer(volunteerRepository.getReferenceById(volunteerId));
        return crewRepository.save(newCrew);
    }

    @Override
    public Crew deleteCrewById(Long volunteerId, Long eventId) {
        Crew existingCrew = crewRepository.findCrewByVolunteerIdAndEventId(volunteerId, eventId);
        if (existingCrew != null) {
            crewRepository.delete(existingCrew);
            return existingCrew;
        }
        return null;
    }

    @Override
    public Crew updateCrew(Long volunteerId, Long eventId, CrewStatus status) {
        Crew existingCrew = crewRepository.findCrewByVolunteerIdAndEventId(volunteerId, eventId);
        if (existingCrew != null) {
            existingCrew.setCrewStatus(status);
            return crewRepository.save(existingCrew);
        }
        return null;
    }

    @Override
    public List<Crew> getAllCrews() {
        return crewRepository.findAll();
    }

    @Override
    public List<Crew> getEventCrew(Long eventId) {
        return crewRepository.findAllByEventId(eventId);
    }

    @Override
    public List<Crew> getVolunteerCrews(Long volunteerId) {
        return crewRepository.findAllByVolunteerId(volunteerId);
    }
}
