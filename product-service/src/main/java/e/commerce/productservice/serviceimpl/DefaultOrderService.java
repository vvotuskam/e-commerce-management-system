package e.commerce.productservice.serviceimpl;

import e.commerce.productservice.entity.OrderEntity;
import e.commerce.productservice.entity.OrderedProductEntity;
import e.commerce.productservice.entity.ProductEntity;
import e.commerce.productservice.entity.enums.OrderStatusEnum;
import e.commerce.productservice.kafka.PaymentProducer;
import e.commerce.productservice.kafka.request.PaymentRequest;
import e.commerce.productservice.mapper.OrderMapper;
import e.commerce.productservice.repository.OrderRepository;
import e.commerce.productservice.repository.OrderedProductRepository;
import e.commerce.productservice.repository.ProductRepository;
import e.commerce.productservice.rest.request.OrderRequest;
import e.commerce.productservice.rest.request.ProductRequest;
import e.commerce.productservice.rest.response.OrderResponse;
import e.commerce.productservice.security.SecurityUserPrincipal;
import e.commerce.productservice.service.OrderService;
import e.commerce.productservice.utils.PaymentUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final OrderMapper orderMapper;
    private final PaymentUtils paymentUtils;
    private final PaymentProducer paymentProducer;

    @Override
    public List<OrderResponse> getUserOrders(Authentication authentication) {
        SecurityUserPrincipal principal = (SecurityUserPrincipal) authentication.getPrincipal();
        return orderRepository.findAllByCustomerEmail(principal.getEmail())
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void commitOrder(OrderRequest request, Authentication authentication) {
        SecurityUserPrincipal principal = (SecurityUserPrincipal) authentication.getPrincipal();

        Map<UUID, Integer> quantityToProduct = getQuantityToProductMap(request.getProducts());

        List<UUID> productIds = request.getProducts().stream()
                .map(product -> UUID.fromString(product.getId()))
                .toList();

        Set<ProductEntity> products = new HashSet<>(productRepository.findAllById(productIds));

        products.forEach(product -> {
            Integer quantity = quantityToProduct.get(product.getId());
            if (quantity > product.getQuantity())
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Not enough products for %s".formatted(product.getId())
                );
        });

        OrderEntity order = OrderEntity.builder()
                .customerEmail(principal.getEmail())
                .orderedAt(LocalDateTime.now())
                .status(OrderStatusEnum.PENDING)
                .build();

        orderRepository.save(order);

        List<OrderedProductEntity> orderedProducts = products.stream()
                .map(product -> OrderedProductEntity.builder()
                        .order(order)
                        .product(product)
                        .quantity(quantityToProduct.get(product.getId()))
                        .build()
                )
                .toList();

        order.setOrderedProducts(orderedProducts);

        orderedProductRepository.saveAll(orderedProducts);

        products.forEach(product -> {
            int newQuantity = product.getQuantity() - quantityToProduct.get(product.getId());
            product.setQuantity(newQuantity);
        });

        PaymentRequest payment = paymentUtils.createPayment(order);
        paymentProducer.sendPayment(payment);
    }

    private Map<UUID, Integer> getQuantityToProductMap(List<ProductRequest> products) {
        return products
                .stream()
                .collect(Collectors.toMap(
                        ProductRequest::getId,
                        ProductRequest::getQuantity,
                        Integer::sum
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> UUID.fromString(entry.getKey()),
                        Map.Entry::getValue
                ));
    }
}
