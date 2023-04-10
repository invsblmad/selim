package com.konzerra.selim_server.domain.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konzerra.selim_server.domain.user.User;
import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_orders_history")
@Data
@NoArgsConstructor
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Column(name = "recording_date", columnDefinition = "TIMESTAMP(0) WITHOUT TIME ZONE")
    private LocalDateTime recordingDate;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public OrderHistory(Order order) {
        this.order = order;
        this.status = OrderStatus.NEW;
        this.recordingDate = LocalDateTime.now();
    }

    public void setDetails(Order order, User user) {
        this.order = order;
        this.user = user;
        this.recordingDate = LocalDateTime.now();
    }
}
