package dev.aaiyvan.transactionaloutbox;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Slf4j
@RequiredArgsConstructor
class OutboxEventDispatcher {

    private final EntityManager entityManager;
    private final boolean removeAfterInsert;

    @EventListener
    @Transactional(propagation = MANDATORY)
    public void on(OutboxEvent<?, ?> event) {
        try (var session = entityManager.unwrap(Session.class)) {
            log.info("An exported event was found for type {}", event.type());

            // Unwrap to Hibernate session and save
            var outbox = new Outbox(event);
            session.persist(outbox);

            // Remove entity if the configuration deems doing so, leaving useful
            if (removeAfterInsert) {
                session.remove(outbox);
            }
        }
    }
}