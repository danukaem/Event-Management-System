package com.assignment.EventManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"host", "attendances"})
public class Event extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    private String location;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private User host;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Attendance> attendances = new ArrayList<>();
}
