package com.example.ism;

import com.example.api.VolunteerApi;
import com.example.model.Volunteer;
import com.example.model.VolunteerWithUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VolunteerController implements VolunteerApi {
    @Override
    public ResponseEntity<Volunteer> createVolunteer(Volunteer volunteer) {
        return VolunteerApi.super.createVolunteer(volunteer);
    }

    @Override
    public ResponseEntity<Void> deleteVolunteer(Integer volunteerId) {
        return VolunteerApi.super.deleteVolunteer(volunteerId);
    }

    @Override
    public ResponseEntity<List<Volunteer>> getAllVolunteers() {
        return VolunteerApi.super.getAllVolunteers();
    }

    @Override
    public ResponseEntity<VolunteerWithUser> getVolunteerById(Integer volunteerId) {
        return VolunteerApi.super.getVolunteerById(volunteerId);
    }

    @Override
    public ResponseEntity<Volunteer> updateVolunteer(Integer volunteerId, Volunteer volunteer) {
        return VolunteerApi.super.updateVolunteer(volunteerId, volunteer);
    }
}
