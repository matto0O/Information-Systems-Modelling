package com.example.ism;

import com.example.api.VolunteerApi;
import com.example.model.Volunteer;
import com.example.model.VolunteerWithUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class VolunteerController implements VolunteerApi {
    private List<Volunteer> volunteers = new ArrayList<>();

    @Override
    public ResponseEntity<Volunteer> createVolunteer(Volunteer volunteer) {
        volunteers.add(volunteer);
        return ResponseEntity.ok(volunteer);
    }

    @Override
    public ResponseEntity<Void> deleteVolunteer(Integer volunteerId) {
        Optional<Volunteer> result = volunteers.stream().findFirst().filter(
                volunteer -> volunteer.getId().equals(volunteerId)
        );

        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Volunteer toDelete = result.get();
        volunteers.remove(toDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Volunteer>> getAllVolunteers() {
        return ResponseEntity.of(Optional.of(volunteers));
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
