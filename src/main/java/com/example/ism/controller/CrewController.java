package com.example.ism.controller;

import com.example.api.CrewApi;
import com.example.model.CrewDTO;
import com.example.model.CrewWithCertificateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CrewController implements CrewApi {
    private final List<CrewDTO> lc = new ArrayList<>();
    private final List<CrewWithCertificateDTO> lcw = new ArrayList<>();

    @Override
    public ResponseEntity<Void> deleteCrew(Integer volunteerId, Integer eventId) {
        Optional<CrewDTO> result = lc.stream().findFirst().filter(
                crewDTO -> crewDTO.getVolunteer().getId().equals(volunteerId) && crewDTO.getEventId().equals(eventId)
        );

        if (result.isPresent()) {
            lc.remove(result.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<CrewDTO>> getAllCrews() {
        return ResponseEntity.of(Optional.of(lc));
    }

    @Override
    public ResponseEntity<List<CrewDTO>> getAllCrewsByEventId(Integer eventId) {
        return ResponseEntity.of(Optional.of(lc.stream().filter(crewDTO -> crewDTO.getEventId().equals(eventId)).toList()));
    }

    @Override
    public ResponseEntity<List<CrewDTO>> getAllCrewsByVolunteerId(Integer volunteerId) {
        return ResponseEntity.of(Optional.of(lc.stream().filter(
                crewDTO -> crewDTO.getVolunteer().getId().equals(volunteerId)).toList())
        );
    }

    @Override
    public ResponseEntity<CrewWithCertificateDTO> getCrewCertificate(Integer volunteerId, Integer eventId) {
        return ResponseEntity.of(Optional.of(lcw.stream().findFirst().filter(
                crew -> crew.getVolunteer().getId().equals(volunteerId) && crew.getEventId().equals(eventId))).get()
        );

    }

    @Override
    public ResponseEntity<Void> joinEventCrew(Integer volunteerId, Integer eventId) {
        CrewDTO crewDTO = new CrewDTO();
        CrewWithCertificateDTO crewWithCertificateDTO = new CrewWithCertificateDTO();
        crewDTO.setEventId(Long.valueOf(eventId));
        crewWithCertificateDTO.setEventId(Long.valueOf(eventId));
//        crewDTO.setVolunteer(volunteerId);
//        crewWithCertificateDTO.setVolunteer(volunteerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<Void> updateCrewStatus(Integer volunteerId, Integer eventId, String status) {
//        return CrewApi.super.updateCrewStatus(volunteerId, eventId, status);
//    }
}
