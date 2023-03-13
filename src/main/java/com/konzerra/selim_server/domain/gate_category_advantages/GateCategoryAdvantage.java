package com.konzerra.selim_server.domain.gate_category_advantages;



import com.konzerra.selim_server.domain.gate_category.GateCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_gate_category_adavantage")
@AllArgsConstructor
public class GateCategoryAdvantage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String text;

    @ManyToOne(cascade =CascadeType.REFRESH,fetch = FetchType.LAZY)
    private GateCategory gateCategory;
}
