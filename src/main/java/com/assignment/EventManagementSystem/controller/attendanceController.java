package com.assignment.EventManagementSystem.controller;

import com.assignment.EventManagementSystem.dto.AttendanceDTO;
import com.assignment.EventManagementSystem.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/attendance")
public class attendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping()
    public ResponseEntity<?> saveAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        return attendanceService.createAttendance(attendanceDTO);
    }


}
