package e.commerce.productservice.service;

import e.commerce.productservice.rest.request.OrderRequest;
import e.commerce.productservice.rest.response.OrderResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getUserOrders(Authentication authentication);

    void commitOrder(OrderRequest request, Authentication authentication);
}
