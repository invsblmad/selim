package com.konzerra.selim_server.domain.order;

import com.konzerra.selim_server.domain.order.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order mapToEntity(OrderDto dto);
}
