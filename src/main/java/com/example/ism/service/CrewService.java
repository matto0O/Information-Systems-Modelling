package com.example.ism.service;

import com.example.ism.model.Crew;
import com.example.ism.model.CrewStatus;

import java.util.List;

public interface CrewService {
    public Crew getCrewMember(Long volunteerId, Long eventId);
    public Crew joinCrew(Long volunteerId, Long eventId);
    public Crew deleteCrewById(Long volunteerId, Long eventId);
    public Crew updateCrew(Long volunteerId, Long eventId, CrewStatus status);
    public List<Crew> getAllCrews();
    public List<Crew> getEventCrew(Long eventId);
    public List<Crew> getVolunteerCrews(Long volunteerId);
}
