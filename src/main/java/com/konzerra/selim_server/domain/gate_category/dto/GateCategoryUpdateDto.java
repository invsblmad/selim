package com.konzerra.selim_server.domain.gate_category.dto;

import com.konzerra.selim_server.domain.gate_category_advantages.dto.GateCategoryAdvantageUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GateCategoryUpdateDto {

    private Long id;

    private String name;

    private String image;

    private String description;

    private List<GateCategoryAdvantageUpdateDto> advantages;

}
