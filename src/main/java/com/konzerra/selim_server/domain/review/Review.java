package com.konzerra.selim_server.domain.review;

import com.konzerra.selim_server.domain.gate_category.GateCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_reviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "customer_image")
    private String customerImage;

    @Column(name = "review_text")
    private String reviewText;

    @ManyToOne
    @JoinColumn(name = "gate_category_id")
    private GateCategory gateCategory;
}

