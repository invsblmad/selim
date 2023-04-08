package com.konzerra.selim_server.domain.order.repository;

import com.konzerra.selim_server.domain.order.model.OrderHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {
    Page<OrderHistory> findByOrder_Id(int id, Pageable pageable);
}
