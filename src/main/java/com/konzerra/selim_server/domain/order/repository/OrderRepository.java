package com.konzerra.selim_server.domain.order.repository;

import com.konzerra.selim_server.domain.order.dto.OrderResponse;
import com.konzerra.selim_server.domain.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
