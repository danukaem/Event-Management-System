package com.assignment.EventManagementSystem.service;

import com.assignment.EventManagementSystem.dto.EventDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface EventService {

    ResponseEntity<?> createEvent(EventDTO eventDTO);

    ResponseEntity<?> updateEvent(EventDTO eventDTO, UUID uuid);

    ResponseEntity<?> deleteEvent(UUID id);

    ResponseEntity<?> filterEvents(String filterBy, String filterValue);

    ResponseEntity<?> getUpcomingEvents(PageRequest pageRequest);

    ResponseEntity<?> checkEventStatus(UUID uuid);

    ResponseEntity<?> getEventsHostOrAttend(UUID uuid);

    ResponseEntity<?> getEventDetails(UUID uuid);
}
