package com.konzerra.selim_server.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
    private int id;
    private String customerName;
    private String customerPhone;
    private String message;
}
