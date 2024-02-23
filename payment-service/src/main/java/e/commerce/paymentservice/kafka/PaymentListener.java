package e.commerce.paymentservice.kafka;

import e.commerce.paymentservice.kafka.request.PaymentRequest;
import e.commerce.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentListener {

    private final PaymentService paymentService;

    @KafkaListener(
            topics = "${spring.kafka.payment-topic}",
            groupId = "kafka-payment-listener",
            containerFactory = "containerFactory"
    )
    public void paymentListener(@Payload PaymentRequest request) {
        log.info("Received new payment from Kafka from user({}), orderId({})", request.getEmail(), request.getOrder().getId());
        paymentService.receivePayment(request);
    }
}
