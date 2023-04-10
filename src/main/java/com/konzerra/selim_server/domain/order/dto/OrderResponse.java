package com.konzerra.selim_server.domain.order.dto;

import com.konzerra.selim_server.domain.order.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
    private int id;
    private String customerName;
    private String customerPhone;
    private String message;
    private OrderStatus currentStatus;
}
