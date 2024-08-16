package dev.aaiyvan.paymentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "eventlog")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class EventLog {

    @Id
    UUID eventId;

    Instant issuedOn;

    public EventLog(UUID eventId) {
        this.eventId = eventId;
        this.issuedOn = Instant.now();
    }
}
