package com.example.ism.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private Event event;

    @ManyToOne
    private Crew assignee;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus taskStatus;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate deadline;

    private double xLocation;

    private double yLocation;
}
