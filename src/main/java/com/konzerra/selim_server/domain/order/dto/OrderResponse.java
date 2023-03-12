package com.konzerra.selim_server.domain.order.dto;

public record OrderResponse(
        int id,
        String customerName,
        String customerPhone,
        String message
) {
}
