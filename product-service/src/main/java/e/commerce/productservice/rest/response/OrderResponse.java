package e.commerce.productservice.rest.response;

import e.commerce.productservice.entity.enums.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private UUID id;
    private LocalDateTime orderedAt;
    private OrderStatusEnum status;
    private List<OrderedProductResponse> orderedProducts;
}
