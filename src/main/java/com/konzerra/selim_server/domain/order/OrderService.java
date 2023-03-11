package com.konzerra.selim_server.domain.order;

import com.konzerra.selim_server.domain.order.dto.OrderRequest;
import com.konzerra.selim_server.domain.common.StatusResponse;
import com.konzerra.selim_server.domain.order.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<StatusResponse> saveOrder(OrderRequest orderRequest);
    Page<OrderResponse> getAllOrders(Pageable pageable);
}
