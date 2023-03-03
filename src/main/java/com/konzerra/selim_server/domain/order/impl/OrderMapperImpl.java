package com.konzerra.selim_server.domain.order.impl;

import com.konzerra.selim_server.domain.order.Order;
import com.konzerra.selim_server.domain.order.OrderMapper;
import com.konzerra.selim_server.domain.order.dto.OrderDto;

public class OrderMapperImpl implements OrderMapper {
    @Override
    public Order mapToEntity(OrderDto dto) {
        Order order = new Order();
        order.setCustomerName(dto.getCustomerName());
        order.setCustomerPhone(dto.getCustomerPhone());
        order.setMessage(dto.getMessage());
        return order;
    }
}
