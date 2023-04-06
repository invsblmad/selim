package com.konzerra.selim_server.domain.order.model;

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
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public OrderHistory(OrderStatus status, LocalDateTime recordingDate, Order order, User user) {
        this.status = status;
        this.recordingDate = recordingDate;
        this.order = order;
        this.user = user;
    }
}
