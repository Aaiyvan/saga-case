package dev.aaiyvan.transactionaloutbox;

import jakarta.persistence.EntityManager;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for TransactionalOutbox.
 * This class provides a bean {@code OutboxEventDispatcher} that is used to listen events.
 */
@Configuration
@EntityScan("dev.aaiyvan.transactionaloutbox")
public class OutboxConfig {

    @Bean
    OutboxEventDispatcher outboxEventDispatcher(EntityManager entityManager) {
        return new OutboxEventDispatcher(entityManager, false);
    }
}