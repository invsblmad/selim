package com.konzerra.selim_server.domain.order;

import com.konzerra.selim_server.domain.order.dto.OrderRequest;
import com.konzerra.selim_server.domain.common.StatusResponse;
import com.konzerra.selim_server.domain.order.dto.OrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<StatusResponse> saveOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return orderService.saveOrder(orderRequest);
    }

    @GetMapping
    public Page<OrderResponse> getAllOrders(Pageable pageable) {
        return orderService.getAllOrders(pageable);
    }
}
