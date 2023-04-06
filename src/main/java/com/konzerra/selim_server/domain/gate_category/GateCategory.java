package com.konzerra.selim_server.domain.gate_category;

import com.konzerra.selim_server.domain.gate.Gate;
import com.konzerra.selim_server.domain.gate_category_advantages.GateCategoryAdvantage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_gate_categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GateCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<GateCategoryAdvantage> advantages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category",fetch = FetchType.LAZY)
    private List<Gate> gates;
}
