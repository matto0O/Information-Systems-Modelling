package com.example.ism.service;

import com.example.ism.model.Organizer;

import java.util.List;

public interface OrganizerService {
    public Organizer addOrganizer(Organizer organizer);
    public Organizer deleteOrganizerById(long id);
    public List<Organizer> findAllOrganizers();
    public Organizer updateOrganizer(long id, Organizer organizer);
    //@Cacheable ("organizers")
    public Organizer findOrganizerById(long id);
}
