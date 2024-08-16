package dev.aaiyvan.paymentservice.model;

import dev.aaiyvan.paymentservice.types.PaymentRequestType;
import dev.aaiyvan.paymentservice.types.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Payment {

    @Id
    UUID orderCreationId;

    Integer guestId;

    Long paymentDue;

    String creditCardNo;

    @Enumerated(EnumType.STRING)
    PaymentRequestType type;

    public PaymentStatus paymentStatus() {
        if (type == null || creditCardNo == null) {
            return PaymentStatus.FAILED;
        }

        PaymentStatus status;
        if (type.isRequest()) {
            if (creditCardNo.endsWith("9999")) {
                status = PaymentStatus.FAILED;
            } else {
                status = PaymentStatus.REQUESTED;
            }
        } else {
            status = PaymentStatus.CANCELLED;
        }

        return status;
    }
}
