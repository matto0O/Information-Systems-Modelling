package com.example.ism.controller;

import com.example.api.OrganizerApi;
import com.example.ism.model.Organizer;
import com.example.ism.service.OrganizerService;
import com.example.ism.service.UserService;
import com.example.model.OrganizerDTO;
import com.example.model.OrganizerWithUserDTO;
import com.example.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrganizerController implements OrganizerApi {

    @Autowired
    OrganizerService organizerService;

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<OrganizerDTO> createOrganizer(OrganizerDTO organizerDTO) {
        Organizer organizer = new Organizer();
        organizer.setId(organizerDTO.getId());
        organizer.setBrandName(organizerDTO.getBrandName());
        organizer.setUser(userService.findUserById(organizerDTO.getUserId()));

        organizerService.addOrganizer(organizer);
        return new ResponseEntity<>(organizerDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteOrganizer(Integer organizerId) {
        if (organizerService.deleteOrganizerById(organizerId) != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<OrganizerDTO>> getAllOrganizers() {
        List<Organizer> organizers = organizerService.findAllOrganizers();
        List<OrganizerDTO> organizerDTOs = new ArrayList<>();
        for (Organizer organizer : organizers) {
            OrganizerDTO organizerDTO = new OrganizerDTO();
            organizerDTO.setId(organizer.getId());
            organizerDTO.setBrandName(organizer.getBrandName());
            organizerDTO.setUserId(organizer.getUser().getId());
            organizerDTOs.add(organizerDTO);
        }
        return new ResponseEntity<>(organizerDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrganizerWithUserDTO> getOrganizerById(Integer organizerId) {
        Organizer organizer = organizerService.findOrganizerById(organizerId);
        if (organizer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        OrganizerWithUserDTO organizerWithUserDTO = new OrganizerWithUserDTO();
        organizerWithUserDTO.setId(organizer.getId());
        organizerWithUserDTO.setBrandName(organizer.getBrandName());
        UserDTO userDTO = new UserDTO();
        userDTO.setId(organizer.getUser().getId());
        userDTO.setEmail(organizer.getUser().getEmail());
        userDTO.setPassword(organizer.getUser().getPassword());
        userDTO.setPhoneNumber(organizer.getUser().getPhoneNumber());
        organizerWithUserDTO.setUser(userDTO);

        return new ResponseEntity<>(organizerWithUserDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrganizerDTO> updateOrganizer(Integer organizerId, OrganizerDTO organizerDTO) {
        Organizer existingOrganizer = organizerService.findOrganizerById(organizerId);
        if (existingOrganizer != null) {
            existingOrganizer.setBrandName(organizerDTO.getBrandName());
            existingOrganizer.setUser(userService.findUserById(organizerDTO.getUserId()));
            organizerService.updateOrganizer(organizerId, existingOrganizer);
        }
        else {
            Organizer organizer = new Organizer();
            organizer.setId(Long.valueOf(organizerId));
            organizer.setBrandName(organizerDTO.getBrandName());
            organizer.setUser(userService.findUserById(organizerDTO.getUserId()));
            organizerService.updateOrganizer(organizerId, organizer);
        }
        return new ResponseEntity<>(organizerDTO, HttpStatus.OK);
    }
}
