package com.konzerra.selim_server.domain.order;

import com.konzerra.selim_server.domain.order.dto.OrderDto;
import com.konzerra.selim_server.domain.common.StatusResponse;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<StatusResponse> saveOrder(OrderDto orderDto);
}
