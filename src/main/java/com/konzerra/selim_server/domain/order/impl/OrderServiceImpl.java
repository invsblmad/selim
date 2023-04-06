package com.konzerra.selim_server.domain.order.impl;

import com.konzerra.selim_server.domain.order.*;
import com.konzerra.selim_server.domain.order.dto.*;
import com.konzerra.selim_server.domain.order.model.Order;
import com.konzerra.selim_server.domain.order.model.OrderHistory;
import com.konzerra.selim_server.domain.order.repository.OrderHistoryRepository;
import com.konzerra.selim_server.domain.order.repository.OrderRepository;
import com.konzerra.selim_server.domain.security.jwt.TokenService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderMapper orderMapper;
    private final TokenService tokenService;

    @Override
    public OrderResponse save(OrderRequest orderRequest) {
        Order order = orderMapper.dtoToEntity(orderRequest);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.entityToDto(savedOrder);
    }

    @Override
    public Page<OrderResponse> getAll(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(orderMapper::entityToDto);
    }

    @Override
    public OrderDetailsResponse getById(int id) {
        Order order = findOrderById(id);
        return orderMapper.entityToDetailsDto(order);
    }

    private Order findOrderById(int id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Order is not found with id " + id)
        );
    }

    @Override
    public Page<OrderHistoryResponse> getHistoryById(int id, Pageable pageable) {
        Page<OrderHistory> history = orderHistoryRepository.findByOrder_Id(id, pageable);
        return history.map(orderMapper::historyEntityToDto);
    }

    @Override
    public OrderHistoryResponse saveRecordToHistory(int id, OrderHistoryRequest orderHistoryRequest) {
        Order order = findOrderById(id);
        OrderHistory history = new OrderHistory(
                orderHistoryRequest.getStatus(), LocalDateTime.now(),
                order, tokenService.getUserFromToken());

        OrderHistory savedHistory = orderHistoryRepository.save(history);
        return orderMapper.historyEntityToDto(savedHistory);
    }

}
