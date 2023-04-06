package com.konzerra.selim_server.domain.gate_category.dto;

import com.konzerra.selim_server.domain.gate_category_advantages.dto.GateCategoryAdvantageSaveDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GateCategorySaveDto {

    private String name;

    private String description;

    private List<GateCategoryAdvantageSaveDto> advantages;

}

