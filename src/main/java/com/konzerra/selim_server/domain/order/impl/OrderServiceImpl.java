package com.konzerra.selim_server.domain.order.impl;

import com.konzerra.selim_server.domain.order.Order;
import com.konzerra.selim_server.domain.order.OrderMapper;
import com.konzerra.selim_server.domain.order.OrderRepository;
import com.konzerra.selim_server.domain.order.OrderService;
import com.konzerra.selim_server.domain.order.dto.OrderRequest;
import com.konzerra.selim_server.domain.common.StatusResponse;
import com.konzerra.selim_server.domain.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public ResponseEntity<StatusResponse> saveOrder(OrderRequest orderRequest) {
        Order order = orderMapper.orderDtoToEntity(orderRequest);
        orderRepository.save(order);
        return ResponseEntity.ok(new StatusResponse(HttpStatus.OK));
    }

    @Override
    public Page<OrderResponse> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(orderMapper::orderEntityToDto);
    }
}
