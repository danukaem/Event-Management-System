package com.assignment.EventManagementSystem.service.impl;

import com.assignment.EventManagementSystem.dto.EventDTO;
import com.assignment.EventManagementSystem.dto.ResponseDTO;
import com.assignment.EventManagementSystem.entity.Event;
import com.assignment.EventManagementSystem.repository.EventRepo;
import com.assignment.EventManagementSystem.repository.UserRepo;
import com.assignment.EventManagementSystem.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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
            Optional<List<Event>> eventByCreatedAt;
            switch (filterBy) {
                case "date":
                    eventByCreatedAt = eventRepo.findEventByCreatedAt(LocalDateTime.parse(filterValue));
                    break;
                case "location":
                    eventByCreatedAt = eventRepo.findEventByLocation(filterValue);
                    break;
                case "visibility":
                    eventByCreatedAt = eventRepo.findEventByVisibility(filterValue);
                    break;
                default:
                    eventByCreatedAt = eventRepo.findEventByLocation(filterValue);


            }
            if (eventByCreatedAt.isPresent()) {
                responseDTO.setStatusCode(HttpStatus.OK);
                responseDTO.setMessage("Fetch Events successfully");
                responseDTO.setData(eventByCreatedAt.get());
                httpStatus = HttpStatus.OK;
            } else {
                responseDTO.setStatusCode(HttpStatus.NOT_FOUND);
                responseDTO.setMessage("Fetch Events failed");
                responseDTO.setData(null);
                httpStatus = HttpStatus.NOT_FOUND;
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
            List<Map<String, Objects>> eventStatus = eventRepo.findEventStatus(uuid);
            if (!eventStatus.isEmpty()) {
                responseDTO.setStatusCode(HttpStatus.OK);
                responseDTO.setMessage("check status of Event successfully");
                responseDTO.setData(eventStatus);
                httpStatus = HttpStatus.OK;
            } else {
                responseDTO.setStatusCode(HttpStatus.NOT_FOUND);
                responseDTO.setMessage("check status of Event failed");
                responseDTO.setData(null);
                httpStatus = HttpStatus.NOT_FOUND;
            }


        } catch (Exception e) {
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST);
            responseDTO.setMessage("check status of Event failed");
            responseDTO.setData(null);
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseDTO, httpStatus);

    }

    @Override
    public ResponseEntity<?> getEventsHostOrAttend(UUID uuid) {
        ResponseDTO responseDTO = new ResponseDTO();
        HttpStatus httpStatus;
        try {
            List<Map<String, Objects>> eventDetails = eventRepo.findEventDetails(uuid);

            if (!eventDetails.isEmpty()) {
                responseDTO.setStatusCode(HttpStatus.OK);
                responseDTO.setMessage("Fetch Events successfully");
                responseDTO.setData(eventDetails);
                httpStatus = HttpStatus.OK;
            } else {
                responseDTO.setStatusCode(HttpStatus.NOT_FOUND);
                responseDTO.setMessage("Fetch Events failed");
                responseDTO.setData(null);
                httpStatus = HttpStatus.NOT_FOUND;
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
    public ResponseEntity<?> getEventDetails(UUID uuid) {
        ResponseDTO responseDTO = new ResponseDTO();
        HttpStatus httpStatus;
        try {

            List<Map<String, Objects>> eventDetailsAttendeeCount = eventRepo.findEventDetailsAttendeeCount(uuid);
            responseDTO.setStatusCode(HttpStatus.OK);
            responseDTO.setMessage("Fetch Events successfully");
            responseDTO.setData(eventDetailsAttendeeCount);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            responseDTO.setStatusCode(HttpStatus.BAD_REQUEST);
            responseDTO.setMessage("Event creation failed");
            responseDTO.setData(null);
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseDTO, httpStatus);

    }


}
