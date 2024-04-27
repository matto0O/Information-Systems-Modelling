package com.example.ism.service;

import com.example.ism.model.Volunteer;

import java.util.List;

public interface VolunteerService {
    public Volunteer addVolunteer(Volunteer volunteer);
    public Volunteer deleteVolunteerById(long id);
    public List<Volunteer> findAllVolunteers();
    public Volunteer updateVolunteer(long id, Volunteer volunteer);
    //@Cacheable ("volunteers")
    public Volunteer findVolunteerById(long id);
}
