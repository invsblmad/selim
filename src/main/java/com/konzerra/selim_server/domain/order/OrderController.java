package com.konzerra.selim_server.domain.order;

import com.konzerra.selim_server.domain.order.dto.OrderDto;
import com.konzerra.selim_server.domain.common.StatusResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("selim/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping()
    public ResponseEntity<StatusResponse> createOrder(@Valid @RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }
}
