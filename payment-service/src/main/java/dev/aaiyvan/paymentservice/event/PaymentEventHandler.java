package dev.aaiyvan.paymentservice.event;

import dev.aaiyvan.paymentservice.model.Payment;
import dev.aaiyvan.paymentservice.repository.EventLogRepository;
import dev.aaiyvan.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class PaymentEventHandler {


    private final ApplicationEventPublisher eventPublisher;
    private final EventLogRepository eventLogRepository;
    private final PaymentRepository paymentRepository;

    public void onPaymentEvent(UUID eventId, UUID sagaId, Payment event) {
        if (eventLogRepository.alreadyProcessed(eventId)) {
            log.info("Event with UUID {} was already retrieved, ignoring it", eventId);
            return;
        }

        paymentRepository.save(event);
        eventPublisher.publishEvent(PaymentEvent.of(sagaId, event.paymentStatus()));
        eventLogRepository.processed(eventId);
    }
}
