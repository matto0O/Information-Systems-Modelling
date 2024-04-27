package com.example.ism.repository;

import com.example.ism.model.Event;
import com.example.ism.model.EventTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
//    public List<Event> findAllBetweenDates(LocalDate startDate, LocalDate endDate);

    public List<Event> findAllByTags(List<EventTag> tags);
}
