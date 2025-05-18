package com.assignment.EventManagementSystem.util;

import com.assignment.EventManagementSystem.entity.Event;
import com.assignment.EventManagementSystem.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component("eventSecurity")
public class EventSecurity {

    @Autowired
    private EventRepo eventRepository;

    public boolean isHostOrAdmin(UUID eventId, Authentication authentication) {
        String username = authentication.getName();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN"));

        if (isAdmin) return true;

        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isEmpty()) return false;

        Event event = optionalEvent.get();
        return event.getHost().getUsername().equals(username);
    }
}
