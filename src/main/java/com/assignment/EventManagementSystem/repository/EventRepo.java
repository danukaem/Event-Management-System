package com.assignment.EventManagementSystem.repository;

import com.assignment.EventManagementSystem.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface EventRepo extends JpaRepository<Event, UUID> {

    @Query(value = "SELECT att.status,att.user_id,att.event_id,evn.host_id FROM events as evn inner join attendances as att on evn.id=att.event_id where evn.id = :eventId", nativeQuery = true)
    List<Map<String, Objects>> findEventStatus(@Param("eventId") UUID eventId);

    @Query(value = "SELECT evn.* FROM events as evn inner join attendances as att on evn.id=att.event_id where evn.host_id = :userId or att.user_id=:userId", nativeQuery = true)
    List<Map<String, Objects>> findEventDetails(@Param("userId") UUID userId);

    Optional<List<Event>> findEventByCreatedAt(LocalDateTime date);

    Optional<List<Event>> findEventByLocation(String location);

    Optional<List<Event>> findEventByVisibility(String visibility);

    @Query(value = "SELECT evn.* , count(att.event_id) as attendee_count FROM events as evn inner join attendances as att on evn.id=att.event_id  where evn.id = :eventId group by evn.id", nativeQuery = true)
    List<Map<String, Objects>> findEventDetailsAttendeeCount(@Param("eventId") UUID eventId);


}
