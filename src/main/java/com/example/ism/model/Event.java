package com.example.ism.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    private String mapPath;

    @ManyToOne
    @JoinColumn(name = "organizerID")
    private Organizer organizer;

    @ManyToMany
//    @JoinTable(name = "events_tags",
//            joinColumns = @JoinColumn(name = "id"),
//            inverseJoinColumns = @JoinColumn(name = "tagID"))
    private Set<EventTag> tags = new HashSet<>();

}
