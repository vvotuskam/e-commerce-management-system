package e.commerce.productservice.mapper;

import e.commerce.productservice.entity.ProductEntity;
import e.commerce.productservice.mapper.base.Mapper;
import e.commerce.productservice.rest.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<ProductEntity, ProductResponse> {

    @Override
    public ProductResponse toResponse(ProductEntity product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }
}
