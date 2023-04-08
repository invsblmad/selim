package com.konzerra.selim_server.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konzerra.selim_server.domain.order.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderHistoryResponse {
    private int id;
    private OrderStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordingDate;
    private String admin;
}
