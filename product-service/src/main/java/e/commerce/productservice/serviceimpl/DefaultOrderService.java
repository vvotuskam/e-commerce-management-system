package e.commerce.productservice.serviceimpl;

import e.commerce.productservice.mapper.OrderMapper;
import e.commerce.productservice.repository.OrderRepository;
import e.commerce.productservice.rest.response.OrderResponse;
import e.commerce.productservice.security.SecurityUserPrincipal;
import e.commerce.productservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderResponse> getUserOrders(Authentication authentication) {
        SecurityUserPrincipal principal = (SecurityUserPrincipal) authentication.getPrincipal();
        return orderRepository.findAllByCustomerEmail(principal.getEmail())
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }
}
