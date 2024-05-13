package com.example.ism.controller;

import com.example.api.VolunteerApi;
import com.example.ism.model.Volunteer;
import com.example.ism.service.UserService;
import com.example.ism.service.VolunteerService;
import com.example.model.UserDTO;
import com.example.model.VolunteerDTO;
import com.example.model.VolunteerWithUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VolunteerController implements VolunteerApi {

    @Autowired
    VolunteerService volunteerService;

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<VolunteerDTO> createVolunteer(VolunteerDTO volunteerDTO) {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(volunteerDTO.getId());
        volunteer.setSurname(volunteerDTO.getSurname());
        volunteer.setName(volunteerDTO.getName());
        volunteer.setUsername(volunteerDTO.getUsername());
        volunteer.setUser(userService.findUserById(volunteerDTO.getUserId()));
        volunteerService.addVolunteer(volunteer);

        return new ResponseEntity<>(volunteerDTO, org.springframework.http.HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteVolunteer(Integer volunteerId) {
        if(volunteerService.deleteVolunteerById(volunteerId) == null) {
            return new ResponseEntity<>(org.springframework.http.HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<VolunteerDTO>> getAllVolunteers() {
        List<Volunteer> volunteers = volunteerService.findAllVolunteers();
        List<VolunteerDTO> volunteerDTOs = new ArrayList<>();
        volunteers.forEach(
                volunteer -> {
                    VolunteerDTO volunteerDTO = new VolunteerDTO();
                    volunteerDTO.setId(volunteer.getId());
                    volunteerDTO.setName(volunteer.getName());
                    volunteerDTO.setSurname(volunteer.getSurname());
                    volunteerDTO.setUsername(volunteer.getUsername());
                    volunteerDTO.setUserId(volunteer.getUser().getId());
                    volunteerDTOs.add(volunteerDTO);
                }
        );

        return new ResponseEntity<>(volunteerDTOs, org.springframework.http.HttpStatus.OK);
    }

    @Override
    public ResponseEntity<VolunteerWithUserDTO> getVolunteerById(Integer volunteerId) {
        Volunteer v = volunteerService.findVolunteerById(volunteerId);

        if(v == null) {
            return new ResponseEntity<>(org.springframework.http.HttpStatus.NOT_FOUND);
        }
        VolunteerWithUserDTO volunteerWithUserDTO = new VolunteerWithUserDTO();
        VolunteerDTO volunteer = new VolunteerDTO();

        volunteer.setId(v.getId());
        volunteer.setName(v.getName());
        volunteer.setSurname(v.getSurname());
        volunteer.setUsername(v.getUsername());
        volunteer.setUserId(v.getUser().getId());

        volunteerWithUserDTO.setVolunteer(volunteer);

        UserDTO user = new UserDTO();
        user.setId(v.getUser().getId());
        user.setEmail(v.getUser().getEmail());
        user.setPassword(v.getUser().getPassword());
        user.setPhoneNumber(v.getUser().getPassword());

        volunteerWithUserDTO.setUser(user);

        return new ResponseEntity<>(volunteerWithUserDTO, org.springframework.http.HttpStatus.OK);
    }

    @Override
    public ResponseEntity<VolunteerDTO> updateVolunteer(Integer volunteerId, VolunteerDTO volunteerDTO) {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(Long.valueOf(volunteerId));
        volunteer.setSurname(volunteerDTO.getSurname());
        volunteer.setName(volunteerDTO.getName());
        volunteer.setUsername(volunteerDTO.getUsername());
        volunteer.setUser(userService.findUserById(volunteerDTO.getUserId()));
        volunteerService.updateVolunteer(volunteerId, volunteer);

        return new ResponseEntity<>(volunteerDTO, org.springframework.http.HttpStatus.OK);
    }
}
