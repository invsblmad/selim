package com.konzerra.selim_server.domain.order.model;

import com.konzerra.selim_server.domain.order.model.OrderHistory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_phone")
    private String customerPhone;
    private String message;

    @OneToMany(mappedBy = "order")
    private List<OrderHistory> history;
}
