package com.assignment.EventManagementSystem.mapper;

import com.assignment.EventManagementSystem.dto.EventDTO;
import com.assignment.EventManagementSystem.entity.Attendance;
import com.assignment.EventManagementSystem.entity.Event;
import com.assignment.EventManagementSystem.entity.User;

import java.util.ArrayList;
import java.util.List;

public class EventMapper {
    public static Event toEntity(EventDTO dto) {
        Event event = new Event();

        event.setId(dto.getEventId());
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setVisibility(dto.getVisibility());
        event.setStartTime(dto.getStartTime());
        event.setEndTime(dto.getEndTime());

        if (dto.getHostId() != null) {
            User host = new User();
            host.setId(dto.getHostId());
            event.setHost(host);
        }

        if (dto.getAttendances() != null) {
            List<Attendance> attendances = new ArrayList<>();
            for (Attendance a : dto.getAttendances()) {
                a.setEvent(event); // Set back-reference
                attendances.add(a);
            }
            event.setAttendances(attendances);
        }

        return event;
    }

    public static EventDTO toDTO(Event event) {
        EventDTO dto = new EventDTO();

        dto.setEventId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setLocation(event.getLocation());
        dto.setVisibility(event.getVisibility());
        dto.setStartTime(event.getStartTime());
        dto.setEndTime(event.getEndTime());

        if (event.getHost() != null) {
            dto.setHostId(event.getHost().getId());
        }

        dto.setAttendances(event.getAttendances()); // Optional: You may want to map to DTOs instead

        return dto;
    }
}
