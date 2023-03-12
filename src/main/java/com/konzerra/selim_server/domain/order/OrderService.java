package com.konzerra.selim_server.domain.order;

import com.konzerra.selim_server.domain.order.dto.OrderRequest;
import com.konzerra.selim_server.domain.order.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponse saveOrder(OrderRequest orderRequest);
    Page<OrderResponse> getAllOrders(Pageable pageable);
}
