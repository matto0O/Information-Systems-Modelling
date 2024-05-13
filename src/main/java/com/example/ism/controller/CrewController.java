package com.example.ism.controller;

import com.example.api.CrewApi;
import com.example.ism.model.Crew;
import com.example.ism.model.CrewStatus;
import com.example.ism.service.CrewService;
import com.example.model.CrewDTO;
import com.example.model.CrewWithCertificateDTO;
import com.example.model.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CrewController implements CrewApi {

    @Autowired
    CrewService crewService;

    @Override
    public ResponseEntity<Void> deleteCrew(Integer volunteerId, Integer eventId) {
        Long vid = Long.valueOf(volunteerId);
        Long eid = Long.valueOf(eventId);
        Crew existingCrew = crewService.getCrewMember(vid, eid);
        if (existingCrew != null) {
            crewService.deleteCrewById(vid, eid);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<CrewDTO>> getAllCrews() {
        List<Crew> crews = crewService.getAllCrews();
        List<CrewDTO> crewDTOs = new ArrayList<>();

        for (Crew crew : crews) {
            CrewDTO crewDTO = new CrewDTO();
            crewDTO.setEventId(crew.getEvent().getId());
            VolunteerDTO volunteerDTO = new VolunteerDTO();
            volunteerDTO.setId(crew.getVolunteer().getId());
            volunteerDTO.setName(crew.getVolunteer().getName());
            volunteerDTO.setSurname(crew.getVolunteer().getSurname());
            volunteerDTO.setUserId(crew.getVolunteer().getUser().getId());
            crewDTO.setVolunteer(volunteerDTO);
            crewDTOs.add(crewDTO);
        }
        return new ResponseEntity<>(crewDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CrewDTO>> getAllCrewsByEventId(Integer eventId) {
        List<Crew> crews = crewService.getEventCrew(Long.valueOf(eventId));
        if(crews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<CrewDTO> crewDTOs = new ArrayList<>();

        for (Crew crew : crews) {
            CrewDTO crewDTO = new CrewDTO();
            crewDTO.setEventId(crew.getEvent().getId());
            VolunteerDTO volunteerDTO = new VolunteerDTO();
            volunteerDTO.setId(crew.getVolunteer().getId());
            volunteerDTO.setName(crew.getVolunteer().getName());
            volunteerDTO.setSurname(crew.getVolunteer().getSurname());
            volunteerDTO.setUserId(crew.getVolunteer().getUser().getId());
            crewDTO.setVolunteer(volunteerDTO);
            crewDTOs.add(crewDTO);
        }
        return new ResponseEntity<>(crewDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CrewDTO>> getAllCrewsByVolunteerId(Integer volunteerId) {
        List<Crew> crews = crewService.getVolunteerCrews(Long.valueOf(volunteerId));
        if(crews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<CrewDTO> crewDTOs = new ArrayList<>();

        for (Crew crew : crews) {
            CrewDTO crewDTO = new CrewDTO();
            crewDTO.setEventId(crew.getEvent().getId());
            VolunteerDTO volunteerDTO = new VolunteerDTO();
            volunteerDTO.setId(crew.getVolunteer().getId());
            volunteerDTO.setName(crew.getVolunteer().getName());
            volunteerDTO.setSurname(crew.getVolunteer().getSurname());
            volunteerDTO.setUserId(crew.getVolunteer().getUser().getId());
            crewDTO.setVolunteer(volunteerDTO);
            crewDTOs.add(crewDTO);
        }
        return new ResponseEntity<>(crewDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CrewWithCertificateDTO> getCrewCertificate(Integer volunteerId, Integer eventId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> joinEventCrew(Integer volunteerId, Integer eventId) {
        Long vid = Long.valueOf(volunteerId);
        Long eid = Long.valueOf(eventId);
        crewService.joinCrew(vid, eid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateCrewStatus(Integer volunteerId, Integer eventId, String status) {
        Long vid = Long.valueOf(volunteerId);
        Long eid = Long.valueOf(eventId);
        Crew existingCrew = crewService.getCrewMember(vid, eid);
        if (existingCrew != null) {
            try {
                CrewStatus crewStatus = CrewStatus.valueOf(status);
                crewService.updateCrew(vid, eid, crewStatus);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
