package com.assignment.EventManagementSystem.dto;

import com.assignment.EventManagementSystem.entity.Attendance;
import com.assignment.EventManagementSystem.entity.Visibility;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDTO {
    private List<Attendance> attendances ;
    private UUID hostId;
    private UUID eventId;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private Visibility visibility;
    private String location;
    private String description;
    @NotBlank(message = "title is required")
    private String title;

}
