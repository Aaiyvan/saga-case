package dev.aaiyvan.transactionaloutbox;

import io.hypersistence.utils.hibernate.type.json.JsonNodeBinaryType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

/**
 * Entity class representing an Outbox Event to store domain events in a persistent store.
 */
@Entity
@NoArgsConstructor
@Table(name = "outboxevent")
@FieldDefaults(level = AccessLevel.PRIVATE)
class Outbox implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    Instant timestamp;

    @Column(name = "aggregateid")
    String aggregateId;

    @Column(name = "aggregatetype")
    String aggregateType;

    String type;

    @Type(JsonNodeBinaryType.class)
    Object payload;

    Outbox(OutboxEvent<?, ?> event) {
        requireNonNull(event, "event cannot be null");
        this.id = UUID.randomUUID();
        this.timestamp = requireNonNull(event.timestamp(), "issuedOn cannot be null");
        this.aggregateId = requireNonNull(event.aggregateId(), "aggregateId cannot be null").toString();
        this.aggregateType = requireNonNull(event.aggregateType(), "aggregateType cannot be null");
        this.type = requireNonNull(event.type(), "type cannot be null");
        this.payload = requireNonNull(event.payload(), "payload cannot be null");
    }
}