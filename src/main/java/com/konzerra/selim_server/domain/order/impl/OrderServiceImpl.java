package com.konzerra.selim_server.domain.order.impl;

import com.konzerra.selim_server.domain.order.OrderService;
import com.konzerra.selim_server.domain.order.dto.OrderHistoryResponse;
import com.konzerra.selim_server.domain.order.dto.OrderRequest;
import com.konzerra.selim_server.domain.order.dto.OrderResponse;
import com.konzerra.selim_server.domain.order.dto.OrderStatusRequest;
import com.konzerra.selim_server.domain.order.mapper.OrderHistoryMapper;
import com.konzerra.selim_server.domain.order.mapper.OrderMapper;
import com.konzerra.selim_server.domain.order.model.Order;
import com.konzerra.selim_server.domain.order.model.OrderHistory;
import com.konzerra.selim_server.domain.order.model.OrderStatus;
import com.konzerra.selim_server.domain.order.repository.OrderHistoryRepository;
import com.konzerra.selim_server.domain.order.repository.OrderRepository;
import com.konzerra.selim_server.domain.security.jwt.TokenService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderMapper orderMapper;
    private final OrderHistoryMapper orderHistoryMapper;
    private final TokenService tokenService;

    @Override
    public OrderResponse save(OrderRequest orderRequest) {
        Order order = orderMapper.dtoToEntity(orderRequest);
        Order savedOrder = orderRepository.save(order);

        OrderHistory history = new OrderHistory(savedOrder);
        orderHistoryRepository.save(history);

        return orderMapper.entityToDto(savedOrder);
    }

    @Override
    public Page<OrderResponse> getAll(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(orderMapper::entityToDto);
    }

    @Override
    public OrderResponse getById(int id) {
        Order order = findOrderById(id);
        return orderMapper.entityToDto(order);
    }

    private Order findOrderById(int id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Order is not found with id " + id)
        );
    }

    @Override
    public Page<OrderHistoryResponse> getOrderHistory(int id, Pageable pageable) {
        Order order = findOrderById(id);
        Page<OrderHistory> history = orderHistoryRepository.findByOrder(order, pageable);
        return history.map(orderHistoryMapper::entityToDto);
    }

    @Override
    public OrderHistoryResponse saveRecordToHistory(int id, OrderStatusRequest orderStatusRequest) {
        Order order = findOrderById(id);
        updateCurrentStatus(order, orderStatusRequest.getStatus());

        OrderHistory history = orderHistoryMapper.dtoToEntity(orderStatusRequest);
        history.setDetails(order, tokenService.getUserFromToken());

        OrderHistory savedHistory = orderHistoryRepository.save(history);
        return orderHistoryMapper.entityToDto(savedHistory);
    }

    @Override
    public void deleteById(int id) {
        Order order = findOrderById(id);
        orderRepository.delete(order);
    }

    private void updateCurrentStatus(Order order, OrderStatus newStatus) {
        order.setCurrentStatus(newStatus);
        orderRepository.save(order);
    }

}
