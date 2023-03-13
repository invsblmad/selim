package com.konzerra.selim_server.domain.gate;


import com.konzerra.selim_server.domain.gate_category.GateCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "tb_gates")
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private GateCategory category;

}
