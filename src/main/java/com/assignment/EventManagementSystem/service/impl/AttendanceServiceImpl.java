package com.assignment.EventManagementSystem.service.impl;

import com.assignment.EventManagementSystem.dto.AttendanceDTO;
import com.assignment.EventManagementSystem.dto.ResponseDTO;
import com.assignment.EventManagementSystem.entity.Attendance;
import com.assignment.EventManagementSystem.mapper.AttendanceMapper;
import com.assignment.EventManagementSystem.repository.AttendanceRepo;
import com.assignment.EventManagementSystem.repository.EventRepo;
import com.assignment.EventManagementSystem.repository.UserRepo;
import com.assignment.EventManagementSystem.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Override
    public ResponseEntity<?> createAttendance(AttendanceDTO attendanceDTO) {

        ResponseDTO responseDTO = new ResponseDTO();
        HttpStatus httpStatus;
        try {
            Attendance entity = AttendanceMapper.toEntity(attendanceDTO);
            attendanceRepo.save(entity);
            responseDTO.setStatusCode(HttpStatus.OK);
            responseDTO.setMessage("Attendance created successfully");
            responseDTO.setData(attendanceDTO);

            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST);
            responseDTO.setMessage("Event creation failed");
            responseDTO.setData(attendanceDTO);
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseDTO, httpStatus);

    }
}
