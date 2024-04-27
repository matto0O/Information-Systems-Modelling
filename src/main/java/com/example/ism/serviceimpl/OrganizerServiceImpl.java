package com.example.ism.serviceimpl;

import com.example.ism.model.Organizer;
import com.example.ism.repository.OrganizerRepository;
import com.example.ism.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class OrganizerServiceImpl implements OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    @Override
    public Organizer addOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    @Override
    public Organizer deleteOrganizerById(long id) {
        Optional<Organizer> c = organizerRepository.findById(id);
        if(c.isPresent()){
            organizerRepository.deleteById(id);
            return c.get();
        }
        return null;
    }

    @Override
    public List<Organizer> findAllOrganizers() {
        return organizerRepository.findAll();
    }

    @Override
    public Organizer updateOrganizer(long id, Organizer organizer) {
        Optional<Organizer> c = organizerRepository.findById(id);
        if(c.isPresent()){
            Organizer existingOrganizer = c.get();
            existingOrganizer.setBrandName(organizer.getBrandName());
            existingOrganizer.setUser(organizer.getUser());
            return organizerRepository.save(existingOrganizer);
        }
        organizer.setId(id);
        return organizerRepository.save(organizer);
    }

    @Override
    public Organizer findOrganizerById(long id) {
        return organizerRepository.findById(id).orElse(null);
    }
}
