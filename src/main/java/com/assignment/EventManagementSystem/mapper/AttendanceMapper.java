package com.assignment.EventManagementSystem.mapper;

import com.assignment.EventManagementSystem.dto.AttendanceDTO;
import com.assignment.EventManagementSystem.entity.Attendance;
import com.assignment.EventManagementSystem.entity.AttendanceId;
import com.assignment.EventManagementSystem.entity.Event;
import com.assignment.EventManagementSystem.entity.User;

public class AttendanceMapper {

    public static Attendance toEntity(AttendanceDTO dto) {
        Attendance attendance = new Attendance();

        AttendanceId id = new AttendanceId();
        id.setUserId(dto.getUserId());
        id.setEventId(dto.getEventId());
        attendance.setId(id);
        User user = new User();
        user.setId(dto.getUserId());
        attendance.setUser(user);

        Event event = new Event();
        event.setId(dto.getEventId());
        attendance.setEvent(event);

        attendance.setStatus(dto.getStatus());
        attendance.setRespondedAt(dto.getRespondedAt());

        return attendance;
    }

    public static AttendanceDTO toDTO(Attendance attendance) {
        AttendanceDTO dto = new AttendanceDTO();
        dto.setUserId(attendance.getUser().getId());
        dto.setEventId(attendance.getEvent().getId());
        dto.setStatus(attendance.getStatus());
        dto.setRespondedAt(attendance.getRespondedAt());

        return dto;
    }

}
