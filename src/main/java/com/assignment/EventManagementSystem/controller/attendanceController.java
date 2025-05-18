package com.assignment.EventManagementSystem.controller;

import com.assignment.EventManagementSystem.dto.AttendanceDTO;
import com.assignment.EventManagementSystem.dto.ResponseDTO;
import com.assignment.EventManagementSystem.entity.Attendance;
import com.assignment.EventManagementSystem.repository.AttendanceRepo;
import com.assignment.EventManagementSystem.repository.EventRepo;
import com.assignment.EventManagementSystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/attendance")
public class attendanceController {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AttendanceRepo attendanceRepo;

    @PostMapping()
    public ResponseEntity<?> saveAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        Attendance attendance = new Attendance();
        attendance.setUser(userRepo.findById(attendanceDTO.getUserId()).get());
        attendance.setEvent(eventRepo.findById(attendanceDTO.getEventId()).get());
        attendance.setStatus(attendanceDTO.getStatus());
        attendance.setRespondedAt(attendanceDTO.getRespondedAt());
        attendanceRepo.save(attendance);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatusCode(HttpStatus.OK);
        responseDTO.setMessage("Attendance created successfully");
        responseDTO.setData(attendanceDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
