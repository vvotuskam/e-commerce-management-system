package e.commerce.productservice.utils;

import e.commerce.productservice.entity.OrderEntity;
import e.commerce.productservice.entity.OrderedProductEntity;
import e.commerce.productservice.kafka.request.PaymentOrderRequest;
import e.commerce.productservice.kafka.request.PaymentRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;

@Component
public class PaymentUtils {

    public PaymentRequest createPayment(OrderEntity order) {
        PaymentOrderRequest orderRequest = new PaymentOrderRequest(
                order.getId().toString(), order.getOrderedAt(), order.getStatus()
        );

        return PaymentRequest.builder()
                .email(order.getCustomerEmail())
                .paymentDate(LocalDateTime.now())
                .amount(calculateOrderAmount(order))
                .order(orderRequest)
                .build();
    }

    private Double calculateOrderAmount(OrderEntity order) {
        double sum = 0;
        Collection<OrderedProductEntity> orderedProducts = order.getOrderedProducts();
        for (OrderedProductEntity product : orderedProducts) {
            double amount = product.getQuantity() * product.getProduct().getPrice();
            sum += amount;
        }
        return sum;
    }
}
