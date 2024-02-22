package e.commerce.productservice.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedProductResponse {
    private UUID id;
    private ProductResponse product;
    private Integer quantity;
}
