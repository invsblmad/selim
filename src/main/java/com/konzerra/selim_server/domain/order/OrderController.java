package com.konzerra.selim_server.domain.order;

import com.konzerra.selim_server.domain.order.dto.OrderRequest;
import com.konzerra.selim_server.domain.order.dto.OrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<OrderResponse> saveOrder(@Valid @RequestBody OrderRequest orderRequest) {
        var response = orderService.saveOrder(orderRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public Page<OrderResponse> getAllOrders(Pageable pageable) {
        return orderService.getAllOrders(pageable);
    }
}
