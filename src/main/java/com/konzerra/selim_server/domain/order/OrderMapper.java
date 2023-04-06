package com.konzerra.selim_server.domain.order;

import com.konzerra.selim_server.domain.order.dto.*;
import com.konzerra.selim_server.domain.order.model.Order;
import com.konzerra.selim_server.domain.order.model.OrderHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface OrderMapper {
    Order dtoToEntity(OrderRequest orderRequest);
    OrderResponse entityToDto(Order order);
    OrderDetailsResponse entityToDetailsDto(Order order);
    @Mapping(source = "orderHistory.user.username", target = "admin")
    OrderHistoryResponse historyEntityToDto(OrderHistory orderHistory);
}
