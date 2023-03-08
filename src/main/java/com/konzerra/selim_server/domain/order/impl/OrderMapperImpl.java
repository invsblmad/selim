package com.konzerra.selim_server.domain.order.impl;

import com.konzerra.selim_server.domain.order.Order;
import com.konzerra.selim_server.domain.order.OrderMapper;
import com.konzerra.selim_server.domain.order.dto.OrderDto;

public class OrderMapperImpl implements OrderMapper {
    @Override
    public Order mapToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setCustomerName(orderDto.getCustomerName());
        order.setCustomerPhone(orderDto.getCustomerPhone());
        order.setMessage(orderDto.getMessage());
        return order;
    }
}
