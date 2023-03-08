package com.konzerra.selim_server.domain.order.impl;

import com.konzerra.selim_server.domain.order.Order;
import com.konzerra.selim_server.domain.order.OrderMapper;
import com.konzerra.selim_server.domain.order.OrderRepository;
import com.konzerra.selim_server.domain.order.OrderService;
import com.konzerra.selim_server.domain.order.dto.OrderDto;
import com.konzerra.selim_server.domain.common.StatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public ResponseEntity<StatusResponse> saveOrder(OrderDto orderDto) {
        Order order = orderMapper.orderDtoToEntity(orderDto);
        orderRepository.save(order);
        return ResponseEntity.ok(new StatusResponse(HttpStatus.OK));
    }
}
