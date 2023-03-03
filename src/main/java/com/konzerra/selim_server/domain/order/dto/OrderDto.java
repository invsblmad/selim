package com.konzerra.selim_server.domain.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class OrderDto {

    @NotBlank(message = "The customer name can't be null or empty")
    private String customerName;

    @NotBlank(message = "The customer phone can't be null or empty")
    private String customerPhone;

    @NotBlank(message = "The message can't be null or empty")
    @Size(max = 255, message = "The length of message exceeds character limit")
    private String message;

}
