package com.assignment.EventManagementSystem.controller;

import com.assignment.EventManagementSystem.repository.AttendanceRepo;
import com.assignment.EventManagementSystem.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private AttendanceRepo attendanceRepo;



//    @PostMapping("saveAttendance")
//    public ResponseEntity<?> saveAttendance(@Valid @RequestBody AttendanceDTO attendanceDTO) {
//        Attendance attendance = new Attendance();
//        attendance.setUser(userRepo.findById(attendanceDTO.getUserId()).get());
//        attendance.setEvent(eventRepo.findById(attendanceDTO.getEventId()).get());
//        attendance.setStatus(attendanceDTO.getStatus());
//        attendance.setRespondedAt(attendanceDTO.getRespondedAt());
//
//        attendanceRepo.save(attendance);
//
//        ResponseDTO responseDTO = new ResponseDTO();
//        responseDTO.setStatusCode(HttpStatus.OK);
//        responseDTO.setMessage("Attendance created successfully");
//        responseDTO.setData(attendanceDTO);
//        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//    }



}
