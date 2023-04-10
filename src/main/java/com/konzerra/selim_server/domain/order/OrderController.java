package com.konzerra.selim_server.domain.order;

import com.konzerra.selim_server.domain.order.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/public/orders")
    public ResponseEntity<OrderResponse> save(@Valid @RequestBody OrderRequest orderRequest) {
        var response = orderService.save(orderRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/protected/orders")
    public Page<OrderResponse> getAll(Pageable pageable) {
        return orderService.getAll(pageable);
    }

    @GetMapping("/protected/orders/{id}")
    public OrderResponse getById(@PathVariable int id) {
        return orderService.getById(id);
    }

    @GetMapping("/protected/orders/{id}/history")
    public Page<OrderHistoryResponse> getOrderHistory(@PathVariable int id, Pageable pageable) {
        return orderService.getOrderHistory(id, pageable);
    }

    @PostMapping("/protected/orders/{id}/history")
    public ResponseEntity<OrderHistoryResponse> saveRecordToHistory(@PathVariable int id,
                                                    @RequestBody OrderStatusRequest orderStatusRequest
    ) {
        var response = orderService.saveRecordToHistory(id, orderStatusRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(response);
    }
}
