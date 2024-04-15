package com.example.ism;

import com.example.api.OrganizerApi;
import com.example.model.Organizer;
import com.example.model.OrganizerWithUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;

@RestController
public class OrganizerController implements OrganizerApi {

    @Override
    public ResponseEntity<Organizer> createOrganizer(Organizer organizer) {
        return OrganizerApi.super.createOrganizer(organizer);
    }

    @Override
    public ResponseEntity<Void> deleteOrganizer(Integer organizerId) {
        return OrganizerApi.super.deleteOrganizer(organizerId);
    }

    @Override
    public ResponseEntity<List<Organizer>> getAllOrganizers() {
        return OrganizerApi.super.getAllOrganizers();
    }

    @Override
    public ResponseEntity<OrganizerWithUser> getOrganizerById(Integer organizerId) {
        return OrganizerApi.super.getOrganizerById(organizerId);
    }

    @Override
    public ResponseEntity<Organizer> updateOrganizer(Integer organizerId, Organizer organizer) {
        return OrganizerApi.super.updateOrganizer(organizerId, organizer);
    }
}
