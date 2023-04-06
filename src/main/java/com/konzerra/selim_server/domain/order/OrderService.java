package com.konzerra.selim_server.domain.order;

import com.konzerra.selim_server.domain.order.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponse save(OrderRequest orderRequest);
    Page<OrderResponse> getAll(Pageable pageable);
    OrderDetailsResponse getById(int id);
    Page<OrderHistoryResponse> getHistoryById(int id, Pageable pageable);
    OrderHistoryResponse saveRecordToHistory(int id, OrderHistoryRequest orderHistoryRequest);
}
