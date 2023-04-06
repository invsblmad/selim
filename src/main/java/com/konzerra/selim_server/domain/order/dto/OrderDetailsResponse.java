package com.konzerra.selim_server.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDetailsResponse {
    private int id;
    private String customerName;
    private String customerPhone;
    private String message;
    private List<OrderHistoryResponse> history;
}
