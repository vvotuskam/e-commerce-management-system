package e.commerce.paymentservice.kafka.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentRequest {
    private String email;
    private Double amount;
    private LocalDateTime paymentDate;
    private PaymentOrderRequest order;
}
