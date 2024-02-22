package e.commerce.productservice.mapper;

import e.commerce.productservice.entity.OrderEntity;
import e.commerce.productservice.mapper.base.Mapper;
import e.commerce.productservice.rest.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper implements Mapper<OrderEntity, OrderResponse> {

    private final OrderedProductMapper orderedProductMapper;

    @Override
    public OrderResponse toResponse(OrderEntity entity) {
        return new OrderResponse(
                entity.getId(),
                entity.getOrderedAt(),
                entity.getStatus(),
                entity.getOrderedProducts().stream().map(orderedProductMapper::toResponse).toList()
        );
    }
}
