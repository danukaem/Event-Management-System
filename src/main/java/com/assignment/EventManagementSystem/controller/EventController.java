package com.assignment.EventManagementSystem.controller;

import com.assignment.EventManagementSystem.dto.EventDTO;
import com.assignment.EventManagementSystem.dto.ResponseDTO;
import com.assignment.EventManagementSystem.entity.Event;
import com.assignment.EventManagementSystem.repository.EventRepo;
import com.assignment.EventManagementSystem.repository.UserRepo;
import com.assignment.EventManagementSystem.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<?> createEvent(@Valid @RequestBody EventDTO eventDTO) {
        return eventService.createEvent(eventDTO);
    }

    @PutMapping("/{id}")//eventId
    @PreAuthorize("@eventSecurity.isHostOrAdmin(#uuid, authentication)")
    public ResponseEntity<?> updateEvent(@Valid @RequestBody EventDTO eventDTO, @PathVariable("id") UUID uuid) {
        return eventService.updateEvent(eventDTO, uuid);
    }

    @DeleteMapping("/{id}")//eventId
    public ResponseEntity<?> deleteEvent(@PathVariable("id") UUID uuid) {
        return eventService.deleteEvent(uuid);
    }

    @GetMapping("/{filterBy}/{filterValue}")
    public ResponseEntity<?> filterEvents(@PathVariable("filterBy") String filterBy, @PathVariable("filterValue") String filterValue) {
        return eventService.filterEvents(filterBy, filterValue);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<?> getUpcomingEvents(@RequestParam("page") int page, @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return eventService.getUpcomingEvents(pageRequest);
    }

    @GetMapping("/{id}/status")//eventId
    public ResponseEntity<?> checkEventStatus(@PathVariable("id") UUID uuid) {
        return eventService.checkEventStatus(uuid);
    }

    @GetMapping("/{id}/events")//userId or hostUserId
    public ResponseEntity<?> getEventsHostOrAttend(@PathVariable("id") UUID uuid) {
        return eventService.getEventsHostOrAttend(uuid);
    }

    @GetMapping("/{id}")//eventId
    public ResponseEntity<?> getEventDetails(@PathVariable("id") UUID uuid) {
        return eventService.getEventDetails(uuid);
    }
}
