package com.konzerra.selim_server.domain.gate_category.dto;

import com.konzerra.selim_server.domain.gate_category_advantages.dto.GateCategoryAdvantageResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GateCategoryResponseDto {

    private Long id;

    private String name;

    private String image;

    private String description;

    private List<GateCategoryAdvantageResponseDto> advantages;

}
