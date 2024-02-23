package e.commerce.productservice.kafka.request;

import e.commerce.productservice.entity.enums.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrderRequest {
    private String id;
    private LocalDateTime orderedAt;
    private OrderStatusEnum status;
}
