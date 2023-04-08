package com.konzerra.selim_server.domain.order.mapper;

import com.konzerra.selim_server.domain.order.dto.*;
import com.konzerra.selim_server.domain.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "currentStatus", constant = "NEW")
    Order dtoToEntity(OrderRequest orderRequest);
    OrderResponse entityToDto(Order order);

}
