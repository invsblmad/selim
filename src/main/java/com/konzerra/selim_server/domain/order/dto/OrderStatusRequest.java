package com.konzerra.selim_server.domain.order.dto;

import com.konzerra.selim_server.domain.order.model.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderStatusRequest {
    private OrderStatus status;
    private String name;
    private LocalDateTime startDate;
}
