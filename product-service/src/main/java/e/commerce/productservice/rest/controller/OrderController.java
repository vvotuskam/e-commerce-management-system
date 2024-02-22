package e.commerce.productservice.rest.controller;

import e.commerce.productservice.rest.request.OrderRequest;
import e.commerce.productservice.rest.response.OrderResponse;
import e.commerce.productservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/my")
    public ResponseEntity<List<OrderResponse>> getUserOrders(Authentication authentication) {
        return ResponseEntity.ok(orderService.getUserOrders(authentication));
    }

    @PostMapping
    public ResponseEntity<Void> commitOrder(
            @RequestBody @Valid OrderRequest request,
            Authentication authentication
    ) {
        orderService.commitOrder(request, authentication);
        return ResponseEntity.noContent().build();
    }
}
