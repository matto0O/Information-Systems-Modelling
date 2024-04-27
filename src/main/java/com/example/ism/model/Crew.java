package com.example.ism.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "crews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "volunteerID")
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(name = "eventID")
    private Event event;

    @Enumerated(value = EnumType.STRING)
    private CrewStatus crewStatus;

    private String certificatePath;


}
