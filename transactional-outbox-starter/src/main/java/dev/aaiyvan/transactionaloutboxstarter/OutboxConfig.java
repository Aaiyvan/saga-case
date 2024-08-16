package dev.aaiyvan.transactionaloutboxstarter;

import jakarta.persistence.EntityManager;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OutboxConfig {

    @Bean
    OutboxEventDispatcher outboxEventDispatcher(EntityManager entityManager) {
        return new OutboxEventDispatcher(entityManager, false);
    }
}
