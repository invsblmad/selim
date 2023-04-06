package com.konzerra.selim_server.domain.gate_category.dto;

import com.konzerra.selim_server.domain.gate.dto.GateResponseDto;
import com.konzerra.selim_server.domain.gate_category_advantages.dto.GateCategoryAdvantageResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GateCategorySimpleResponseDto {

    private Long id;

    private String name;
}
