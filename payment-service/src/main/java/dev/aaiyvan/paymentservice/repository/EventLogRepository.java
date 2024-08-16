package dev.aaiyvan.paymentservice.repository;

import dev.aaiyvan.paymentservice.model.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface EventLogRepository extends JpaRepository<EventLog, UUID> {

    default void processed(UUID eventId) {
        save(new EventLog(eventId));
    }

    @Query("""
            SELECT COUNT (cm.eventId)=1
            FROM EventLog cm
            WHERE cm.eventId = :eventId
            """)
    boolean alreadyProcessed(UUID eventId);

}
