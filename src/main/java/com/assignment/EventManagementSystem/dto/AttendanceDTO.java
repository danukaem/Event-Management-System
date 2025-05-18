package com.assignment.EventManagementSystem.dto;

import com.assignment.EventManagementSystem.entity.AttendanceStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AttendanceDTO {
    @NotBlank(message = "userId is required")
    private UUID userId;

    @NotBlank(message = "eventId is required")
    private UUID eventId;

    @NotBlank(message = "status is required")
    private AttendanceStatus status;

    private LocalDateTime respondedAt;

}
