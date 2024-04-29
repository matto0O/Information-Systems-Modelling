package com.example.ism.serviceimpl;

import com.example.ism.model.Volunteer;

import com.example.ism.repository.VolunteerRepository;
import com.example.ism.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Override
    public Volunteer addVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer deleteVolunteerById(long id) {
        Optional<Volunteer> v = volunteerRepository.findById(id);
        if(v.isPresent()){
            volunteerRepository.deleteById(id);
            return v.get();
        }
        return null;
    }

    @Override
    public List<Volunteer> findAllVolunteers() {
        return volunteerRepository.findAll();
    }

    @Override
    public Volunteer updateVolunteer(long id, Volunteer volunteer) {
        Optional<Volunteer> v = volunteerRepository.findById(id);
        if(v.isPresent()){
            Volunteer existingVolunteer = v.get();
            existingVolunteer.setName(volunteer.getName());
            existingVolunteer.setSurname(volunteer.getSurname());
            existingVolunteer.setUser(volunteer.getUser());
            existingVolunteer.setUsername(volunteer.getUsername());
            return volunteerRepository.save(existingVolunteer);
        }
        volunteer.setId(id);

        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer findVolunteerById(long id) {
        return volunteerRepository.findById(id).orElse(null);
    }
}
