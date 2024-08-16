package dev.aaiyvan.transactionaloutboxstarter;

import io.hypersistence.utils.hibernate.type.json.JsonNodeBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

/**
 * Entity class representing an Outbox Event to store domain events in a persistent store.
 */
@Entity
@Table(name = "outboxevent")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
class Outbox implements Serializable {

    @Id
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
        this.id = UUID.randomUUID();
        this.timestamp = event.timestamp();
        this.aggregateId = event.aggregateId().toString();
        this.aggregateType = event.aggregateType();
        this.type = event.type();
        this.payload = event.payload();
    }
}
