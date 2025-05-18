package com.assignment.EventManagementSystem.repository;

import com.assignment.EventManagementSystem.entity.Attendance;
import com.assignment.EventManagementSystem.entity.AttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepo extends JpaRepository<Attendance, AttendanceId> {
}
