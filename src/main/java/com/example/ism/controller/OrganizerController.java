package com.example.ism.controller;

import com.example.api.OrganizerApi;
import com.example.model.OrganizerDTO;
import com.example.model.OrganizerWithUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrganizerController implements OrganizerApi {

    @Override
    public ResponseEntity<OrganizerDTO> createOrganizer(OrganizerDTO organizerDTO) {
        return OrganizerApi.super.createOrganizer(organizerDTO);
    }

    @Override
    public ResponseEntity<Void> deleteOrganizer(Integer organizerId) {
        return OrganizerApi.super.deleteOrganizer(organizerId);
    }

    @Override
    public ResponseEntity<List<OrganizerDTO>> getAllOrganizers() {
        return OrganizerApi.super.getAllOrganizers();
    }

    @Override
    public ResponseEntity<OrganizerWithUserDTO> getOrganizerById(Integer organizerId) {
        return OrganizerApi.super.getOrganizerById(organizerId);
    }

    @Override
    public ResponseEntity<OrganizerDTO> updateOrganizer(Integer organizerId, OrganizerDTO organizerDTO) {
        return OrganizerApi.super.updateOrganizer(organizerId, organizerDTO);
    }
}
