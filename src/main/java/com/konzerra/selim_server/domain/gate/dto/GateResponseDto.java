package com.konzerra.selim_server.domain.gate.dto;

import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GateResponseDto {
    private Long id;
    private String name;
    private String image;
    private GateCategoryResponseDto category;


}

