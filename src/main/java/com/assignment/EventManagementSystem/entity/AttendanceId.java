package com.assignment.EventManagementSystem.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
public class AttendanceId implements Serializable {

    private UUID userId;

    private UUID eventId;

    // equals() and hashCode() required!
}
