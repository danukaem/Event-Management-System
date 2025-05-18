package com.assignment.EventManagementSystem.service.impl;

import com.assignment.EventManagementSystem.dto.EventDTO;
import com.assignment.EventManagementSystem.dto.ResponseDTO;
import com.assignment.EventManagementSystem.entity.Event;
import com.assignment.EventManagementSystem.repository.EventRepo;
import com.assignment.EventManagementSystem.repository.UserRepo;
import com.assignment.EventManagementSystem.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public ResponseEntity<?> createEvent(EventDTO eventDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        HttpStatus httpStatus;

        try {
            Event event = new Event();
            event.setAttendances(eventDTO.getAttendances());
            event.setTitle(eventDTO.getTitle());
            event.setDescription(eventDTO.getDescription());
            event.setLocation(eventDTO.getLocation());
            event.setVisibility(eventDTO.getVisibility());
            event.setStartTime(eventDTO.getStartTime());
            event.setEndTime(eventDTO.getEndTime());
            if (userRepo.findById(eventDTO.getHostId()).isPresent()) {
                event.setHost(userRepo.findById(eventDTO.getHostId()).get());
            }
            Event savedEvent = eventRepo.save(event);
            eventDTO.setAttendances(savedEvent.getAttendances());
            eventDTO.setEventId(savedEvent.getId());

            responseDTO.setStatusCode(HttpStatus.OK);
            responseDTO.setMessage("Event created successfully");
            responseDTO.setData(eventDTO);
            httpStatus = HttpStatus.OK;

        } catch (Exception e) {
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST);
            responseDTO.setMessage("Event creation failed");
            responseDTO.setData(eventDTO);
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseDTO, httpStatus);

    }

    @Override
    public ResponseEntity<?> updateEvent(EventDTO eventDTO, UUID uuid) {
        ResponseDTO responseDTO = new ResponseDTO();
        HttpStatus httpStatus;
        try {
            Optional<Event> eventById = eventRepo.findById(uuid);
            if (eventById.isPresent()) {
                Event event = eventById.get();
                event.setTitle(eventDTO.getTitle());
                event.setDescription(eventDTO.getDescription());
                event.setLocation(eventDTO.getLocation());
                event.setVisibility(eventDTO.getVisibility());
                event.setStartTime(eventDTO.getStartTime());
                event.setEndTime(eventDTO.getEndTime());
                event.setAttendances(eventDTO.getAttendances());
                Event savedevent = eventRepo.save(event);

                responseDTO.setStatusCode(HttpStatus.OK);
                responseDTO.setMessage("Event updated successfully");
                responseDTO.setData(savedevent);
                httpStatus = HttpStatus.OK;
            } else {
                responseDTO.setStatusCode(HttpStatus.NOT_FOUND);
                responseDTO.setMessage("Event not found");
                responseDTO.setData(eventDTO);
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST);
            responseDTO.setMessage("Event creation failed");
            responseDTO.setData(eventDTO);
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseDTO, httpStatus);

    }

    @Override
    public ResponseEntity<?> deleteEvent(UUID id) {
        ResponseDTO responseDTO = new ResponseDTO();
        HttpStatus httpStatus;
        try {
            Optional<Event> eventById = eventRepo.findById(id);
            if (eventById.isPresent()) {
                Event event = eventById.get();
                event.setDeleted(true);
                eventRepo.save(event);
                responseDTO.setStatusCode(HttpStatus.OK);
                responseDTO.setMessage("Event deleted successfully");
                responseDTO.setData(event);
                httpStatus = HttpStatus.OK;
            } else {
                responseDTO.setStatusCode(HttpStatus.NOT_FOUND);
                responseDTO.setMessage("Event not Found");
                responseDTO.setData(null);
                httpStatus = HttpStatus.OK;
            }

        } catch (Exception e) {
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST);
            responseDTO.setMessage("Event creation failed");
            responseDTO.setData(null);
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseDTO, httpStatus);

    }

    @Override
    public ResponseEntity<?> filterEvents(String filterBy, String filterValue) {
        ResponseDTO responseDTO = new ResponseDTO();
        HttpStatus httpStatus;
        try {

        } catch (Exception e) {
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST);
            responseDTO.setMessage("Event creation failed");
//            responseDTO.setData(eventDTO);
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getUpcomingEvents(PageRequest pageRequest) {
        ResponseDTO responseDTO = new ResponseDTO();
        HttpStatus httpStatus;
        try {
            responseDTO.setStatusCode(HttpStatus.OK);
            responseDTO.setMessage("Fetch Events successfully");
            responseDTO.setData(eventRepo.findAll(pageRequest));
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST);
            responseDTO.setMessage("fetching upcoming Events failed");
            responseDTO.setData(null);
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseDTO, httpStatus);
    }

    @Override
    public ResponseEntity<?> checkEventStatus(UUID uuid) {
        ResponseDTO responseDTO = new ResponseDTO();
        HttpStatus httpStatus;
        try {

        } catch (Exception e) {
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST);
            responseDTO.setMessage("Event creation failed");
//            responseDTO.setData(eventDTO);
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getEventsHostOrAttend(UUID uuid) {
        ResponseDTO responseDTO = new ResponseDTO();
        HttpStatus httpStatus;
        try {

        } catch (Exception e) {
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST);
            responseDTO.setMessage("Event creation failed");
//            responseDTO.setData(eventDTO);
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getEventDetails(UUID uuid) {
        ResponseDTO responseDTO = new ResponseDTO();
        HttpStatus httpStatus;
        try {

        } catch (Exception e) {
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST);
            responseDTO.setMessage("Event creation failed");
//            responseDTO.setData(eventDTO);
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return null;
    }


}
