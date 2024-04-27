package com.example.ism.repository;

import com.example.ism.model.Crew;
import com.example.ism.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewRepository extends JpaRepository<Crew, Long> {
    List<Crew> findAllByVolunteerId(Long volunteerId);

    List<Crew> findAllByEventId(Long eventId);

    Crew findCrewByVolunteerIdAndEventId(Long volunteerId, Long eventId);
}
