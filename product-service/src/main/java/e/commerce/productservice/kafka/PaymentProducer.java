package e.commerce.productservice.kafka;

import e.commerce.productservice.kafka.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaProperties properties;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendPayment(PaymentRequest request) {
        kafkaTemplate.send(properties.getPaymentTopic(), request);
    }
}
