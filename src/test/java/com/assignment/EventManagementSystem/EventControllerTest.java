package com.assignment.EventManagementSystem;

import com.assignment.EventManagementSystem.controller.EventController;
import com.assignment.EventManagementSystem.dto.EventDTO;
import com.assignment.EventManagementSystem.dto.ResponseDTO;
import com.assignment.EventManagementSystem.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@WebMvcTest(EventController.class)
class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEvent() {
        EventDTO eventDTO = new EventDTO();
        ResponseEntity<?> expectedResponse = ResponseEntity.ok("Event created");
        when(eventService.createEvent(eventDTO)).thenReturn(expectedResponse);

        ResponseEntity<?> actualResponse = eventController.createEvent(eventDTO);

        assertEquals(expectedResponse, actualResponse);
        verify(eventService, times(1)).createEvent(eventDTO);
    }

    @Test
    void testUpdateEvent() {
        UUID eventId = UUID.randomUUID();
        EventDTO eventDTO = new EventDTO();
        ResponseEntity<?> expectedResponse = ResponseEntity.ok("Event updated");
        when(eventService.updateEvent(eventDTO, eventId)).thenReturn(expectedResponse);

        ResponseEntity<?> actualResponse = eventController.updateEvent(eventDTO, eventId);

        assertEquals(expectedResponse, actualResponse);
        verify(eventService, times(1)).updateEvent(eventDTO, eventId);
    }

    @Test
    void testDeleteEvent() {
        UUID eventId = UUID.randomUUID();
        ResponseEntity<?> expectedResponse = ResponseEntity.ok("Event deleted");
        when(eventService.deleteEvent(eventId)).thenReturn(expectedResponse);

        ResponseEntity<?> actualResponse = eventController.deleteEvent(eventId);

        assertEquals(expectedResponse, actualResponse);
        verify(eventService, times(1)).deleteEvent(eventId);
    }

    @Test
    void testGetUpcomingEvents() {
        int page = 0;
        int size = 10;
        ResponseEntity<?> expectedResponse = ResponseEntity.ok("Upcoming events");
        when(eventService.getUpcomingEvents(any())).thenReturn(expectedResponse);

        ResponseEntity<?> actualResponse = eventController.getUpcomingEvents(page, size);

        assertEquals(expectedResponse, actualResponse);
        verify(eventService, times(1)).getUpcomingEvents(any());
    }

    @Test
    void testCheckEventStatus() {
        UUID eventId = UUID.randomUUID();
        ResponseEntity<?> expectedResponse = ResponseEntity.ok("Event status");
        when(eventService.checkEventStatus(eventId)).thenReturn(expectedResponse);

        ResponseEntity<?> actualResponse = eventController.checkEventStatus(eventId);

        assertEquals(expectedResponse, actualResponse);
        verify(eventService, times(1)).checkEventStatus(eventId);
    }

    @Test
    void testGetEventsHostOrAttend() {
        UUID userId = UUID.randomUUID();
        ResponseEntity<?> expectedResponse = ResponseEntity.ok("Events hosted or attended");
        when(eventService.getEventsHostOrAttend(userId)).thenReturn(expectedResponse);

        ResponseEntity<?> actualResponse = eventController.getEventsHostOrAttend(userId);

        assertEquals(expectedResponse, actualResponse);
        verify(eventService, times(1)).getEventsHostOrAttend(userId);
    }

    @Test
    void testGetEventDetails() {
        UUID eventId = UUID.randomUUID();
        ResponseEntity<?> expectedResponse = ResponseEntity.ok("Event details");
        when(eventService.getEventDetails(eventId)).thenReturn(expectedResponse);

        ResponseEntity<?> actualResponse = eventController.getEventDetails(eventId);

        assertEquals(expectedResponse, actualResponse);
        verify(eventService, times(1)).getEventDetails(eventId);
    }

}