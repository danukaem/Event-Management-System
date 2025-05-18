package com.assignment.EventManagementSystem.service;

import com.assignment.EventManagementSystem.dto.AttendanceDTO;
import org.springframework.http.ResponseEntity;

public interface AttendanceService {

    ResponseEntity<?> createAttendance(AttendanceDTO attendanceDTO);
}
