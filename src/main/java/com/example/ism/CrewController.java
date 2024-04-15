package com.example.ism;

import com.example.api.CrewApi;
import com.example.model.Crew;
import com.example.model.CrewWithCertificate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CrewController implements CrewApi {
    private final List<Crew> lc = new ArrayList<>();
    private final List<CrewWithCertificate> lcw = new ArrayList<>();

    @Override
    public ResponseEntity<Void> deleteCrew(Integer volunteerId, Integer eventId) {
        Optional<Crew> result = lc.stream().findFirst().filter(
                crew -> crew.getVolunteer().getId().equals(volunteerId) && crew.getEventId().equals(eventId)
        );

        if (result.isPresent()) {
            lc.remove(result.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Crew>> getAllCrews() {
        return ResponseEntity.of(Optional.of(lc));
    }

    @Override
    public ResponseEntity<List<Crew>> getAllCrewsByEventId(Integer eventId) {
        return ResponseEntity.of(Optional.of(lc.stream().filter(crew -> crew.getEventId().equals(eventId)).toList()));
    }

    @Override
    public ResponseEntity<List<Crew>> getAllCrewsByVolunteerId(Integer volunteerId) {
        return ResponseEntity.of(Optional.of(lc.stream().filter(
                crew -> crew.getVolunteer().getId().equals(volunteerId)).toList())
        );
    }

    @Override
    public ResponseEntity<CrewWithCertificate> getCrewCertificate(Integer volunteerId, Integer eventId) {
        return ResponseEntity.of(Optional.of(lcw.stream().findFirst().filter(
                crew -> crew.getVolunteer().getId().equals(volunteerId) && crew.getEventId().equals(eventId))).get()
        );

    }

    @Override
    public ResponseEntity<Void> joinEventCrew(Integer volunteerId, Integer eventId) {
        Crew crew = new Crew();
        CrewWithCertificate crewWithCertificate = new CrewWithCertificate();
        crew.setEventId(Long.valueOf(eventId));
        crewWithCertificate.setEventId(Long.valueOf(eventId));
//        crew.setVolunteer(volunteerId);
//        crewWithCertificate.setVolunteer(volunteerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<Void> updateCrewStatus(Integer volunteerId, Integer eventId, String status) {
//        return CrewApi.super.updateCrewStatus(volunteerId, eventId, status);
//    }
}
