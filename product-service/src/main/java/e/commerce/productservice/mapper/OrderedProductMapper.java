package e.commerce.productservice.mapper;

import e.commerce.productservice.entity.OrderedProductEntity;
import e.commerce.productservice.mapper.base.Mapper;
import e.commerce.productservice.rest.response.OrderedProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderedProductMapper implements Mapper<OrderedProductEntity, OrderedProductResponse> {

    private final ProductMapper productMapper;

    @Override
    public OrderedProductResponse toResponse(OrderedProductEntity entity) {
        return new OrderedProductResponse(
                entity.getId(),
                productMapper.toResponse(entity.getProduct()),
                entity.getQuantity()
        );
    }
}
