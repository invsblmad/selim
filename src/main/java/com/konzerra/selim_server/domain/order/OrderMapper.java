package com.konzerra.selim_server.domain.order;

import com.konzerra.selim_server.domain.order.dto.OrderRequest;
import com.konzerra.selim_server.domain.order.dto.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order orderDtoToEntity(OrderRequest orderRequest);
    OrderResponse orderEntityToDto(Order order);
}
