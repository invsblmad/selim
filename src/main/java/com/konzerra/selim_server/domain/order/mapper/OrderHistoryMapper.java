package com.konzerra.selim_server.domain.order.mapper;

import com.konzerra.selim_server.domain.order.dto.OrderStatusRequest;
import com.konzerra.selim_server.domain.order.dto.OrderHistoryResponse;
import com.konzerra.selim_server.domain.order.model.OrderHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderHistoryMapper {

    @Mapping(source = "orderHistory.user.username", target = "admin")
    OrderHistoryResponse entityToDto(OrderHistory orderHistory);
    OrderHistory dtoToEntity(OrderStatusRequest orderStatusRequest);
}
